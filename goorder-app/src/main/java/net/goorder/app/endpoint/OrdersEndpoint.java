package net.goorder.app.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.goorder.app.presentation.OrderGroupView;
import net.goorder.app.presentation.OrderingTableView;

/**
 *
 * @author witoldsz
 */
@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdersEndpoint {

    @Path("/group/{groupId}")
    @GET
    public OrderGroupView group(@PathParam("groupId") String groupId) {
        return null;
    }

    @Path("/group/{groupId}/table/{tableId}")
    @GET
    public OrderingTableView table(@PathParam("groupId") String groupId, @PathParam("tableId") Long tableId) {
        return null;
    }
}
