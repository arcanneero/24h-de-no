package net.arcann.telethonno.endpoint;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
@Slf4j
public class OptionsFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
        if (requestContext.getRequest().getMethod().equals("OPTIONS")) {
            log.info("HTTP Method (OPTIONS) - Detected!");

            // Just send a OK signal back to the browser
            requestContext.abortWith(Response.status(Response.Status.OK).build());
        }

    }

}
