package com.rho.clickcounter.rest.resources;

import com.rho.clickcounter.rest.Paths;
import com.rho.clickcounter.rest.dto.ClickCounterEntity;
import com.rho.clickcounter.services.ClickCounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Hugo Seabra (hugo.d.seabra@gmail.com)
 */
@Path(Paths.TICKET_COUNTER)
public class ClickCounterResource {
    private static final Logger logger = LoggerFactory.getLogger(ClickCounterResource.class);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClickCounter() {
        logger.info("getting counter...");
        Integer counter = ClickCounterService.getInstance().getClickCounter();
        ClickCounterEntity entity = new ClickCounterEntity(counter);
        return Response.ok().entity(entity).build();
    }
}
