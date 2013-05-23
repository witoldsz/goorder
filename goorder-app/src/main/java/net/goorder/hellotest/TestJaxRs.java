package net.goorder.hellotest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Witold Szczerba
 */
@Path("/hello/jaxrs")
public class TestJaxRs {
    
    @Inject
    private TestCdiBean cdiBean;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from JAX-RS.\n" + cdiBean.greet();
    }
}
