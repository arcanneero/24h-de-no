package net.arcann.telethonno.endpoint.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.arcann.telethonno.engine.business.api.events.SseWriteableEvent;
import org.glassfish.jersey.media.sse.EventOutput;

import java.util.function.Function;

@AllArgsConstructor
@Data
public class SseEventDestination {

    EventOutput output;

    Function<SseWriteableEvent, Boolean> eventFilter;
}
