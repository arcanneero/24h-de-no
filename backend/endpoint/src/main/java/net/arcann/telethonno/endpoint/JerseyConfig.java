package net.arcann.telethonno.endpoint;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;

@Configuration
public class JerseyConfig extends ResourceConfig {

    private final ApplicationContext context;

    public JerseyConfig(ApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    private void registerEndpoints() {
        context.getBeansWithAnnotation(Path.class).forEach((s, o) -> this.register(o.getClass()));

        register(CORSFilter.class);
        register(OptionsFilter.class);
        register(ObjectMapperContextResolver.class);
    }
}
