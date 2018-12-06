package net.arcann.telethonno.endpoint.events;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.arcann.telethonno.engine.business.api.events.SseWriteableEvent;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Component
@Slf4j
public class SseNotifier {

    private final Set<SseEventDestination> connectionMap = new HashSet<>();

    private final ExecutorService messageExecutorService;


    public SseNotifier() {
        messageExecutorService = Executors.newSingleThreadExecutor();
    }

    public EventOutput register(Function<SseWriteableEvent, Boolean> eventFilter) {
        final EventOutput eventOutput = new EventOutput();
        log.info("adding connection");
        connectionMap.add(new SseEventDestination(eventOutput, eventFilter));
        return eventOutput;
    }

    public void shutdown() {
        if (messageExecutorService != null && !messageExecutorService.isShutdown()) {
            log.info("SseWriteManager.shutdown: calling messageExecutorService.shutdown.");
            messageExecutorService.shutdown();
        } else {
            log.info("SseWriteManager.shutdown: messageExecutorService == null || messageExecutorService.isShutdown().");
        }

    }

    @EventListener(SseWriteableEvent.class)
    public void handleSseWriteableEvent(SseWriteableEvent event) {
        messageExecutorService.submit(new MessageProcessor(event));
    }

    @RequiredArgsConstructor
    private class MessageProcessor implements Runnable {

        @NonNull
        SseWriteableEvent event;

        @Override
        public void run() {
            Iterator<SseEventDestination> iterator = connectionMap.iterator();
            while (iterator.hasNext()) {
                boolean remove = false;
                SseEventDestination entry = iterator.next();
                EventOutput eventOutput = entry.getOutput();
                if (eventOutput != null) {
                    if (eventOutput.isClosed()) {
                        remove = true;
                    } else {
                        log.info("trying");
                        if (entry.getEventFilter().apply(event)) {
                            try {
                                log.info("writing {}", event);
                                eventOutput.write(
                                        new OutboundEvent.Builder()//
                                                .mediaType(javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE)//
                                                .name(event.getClass().getSimpleName())//
                                                .data(event.getClass(), event)//
                                                .build()
                                );
                            } catch (Exception ex) {
                                log.info(String.format("write failed", ex));
                                remove = true;
                            }
                        }
                    }
                }
                if (remove) {
                    // we are removing the eventOutput. close it is if it not already closed.
                    if (!eventOutput.isClosed()) {
                        try {
                            eventOutput.close();
                        } catch (Exception ex) {
                            // do nothing.
                        }
                    }
                    iterator.remove();
                }
            }
        }
    }
}