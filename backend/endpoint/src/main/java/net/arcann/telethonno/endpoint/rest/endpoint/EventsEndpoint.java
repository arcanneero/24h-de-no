package net.arcann.telethonno.endpoint.rest.endpoint;

import lombok.extern.slf4j.Slf4j;
import net.arcann.telethonno.endpoint.events.SseNotifier;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

@Slf4j
@Component
@Path("/resultats")
public class EventsEndpoint {

    @Autowired
    private SseNotifier notifier;

    @Produces(SseFeature.SERVER_SENT_EVENTS)
    @ResponseBody
    @GET
    public EventOutput subscribe() throws IOException {
        return notifier.register(e -> true);
    }
}
