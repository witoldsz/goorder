package net.goorder.app.infrastructure;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

/**
 * Implements Message Body Reader/Write for Jackson POJO or JAXB annotated classes.
 * @author witoldsz
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class EnableJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {
    
}
