package net.arcann.telethonno.endpoint;

import net.arcann.telethonno.endpoint.rest.endpoint.EventsEndpoint;
import net.arcann.telethonno.endpoint.rest.endpoint.PisteEndpoint;
import net.arcann.telethonno.endpoint.rest.endpoint.AdminEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void registerEndpoints() {
        register(PisteEndpoint.class);
        register(AdminEndpoint.class);
        register(EventsEndpoint.class);

        register(CORSFilter.class);
        register(OptionsFilter.class);
        register(ObjectMapperContextResolver.class);
    }
}
