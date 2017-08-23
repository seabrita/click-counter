package com.rho.clickcounter.rest.resources;

import com.rho.clickcounter.rest.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Hugo Seabra (hugo.d.seabra@gmail.com)
 */
@Path(Paths.ROOT)
public class HomeResource {
    @GET
    @Produces("text/html")
    public Response test() {
        String test = "<html><body><h1>Ticket Counter Backend Server</h1><p>it works!</p></body></html>";
        return Response.ok().entity(test).build();
    }
}
