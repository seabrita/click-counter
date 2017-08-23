package com.rho.clickcounter.rest.resources;

import com.rho.clickcounter.rest.Paths;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;

/**
 * @author Hugo Seabra (hugo.d.seabra@gmail.com)
 */
public class HomeResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(HomeResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path(Paths.ROOT).request().get(String.class);

        assertEquals("<html><body><h1>Ticket Counter Backend Server</h1><p>it works!</p></body></html>", responseMsg);
    }
}
