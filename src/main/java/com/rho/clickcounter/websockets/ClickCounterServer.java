package com.rho.clickcounter.websockets;

import com.rho.clickcounter.rest.Paths;
import com.rho.clickcounter.services.ClickCounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hugo Seabra (hugo.d.seabra@gmail.com)
 */
@ServerEndpoint(Paths.TICKET_COUNTER_WEB_SOCKET)
public class ClickCounterServer {
    private static final Logger logger = LoggerFactory.getLogger(ClickCounterServer.class);
    private static final Set<Session> sessions =
            Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        logger.info(session.getId() + " has opened a connection");
        sessions.add(session);
        try {
            Integer clickCounter = ClickCounterService.getInstance().getClickCounter();
            session.getBasicRemote().sendText(clickCounter.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("Message from " + session.getId() + ": " + message);
        if ("increment".equalsIgnoreCase(message)) {
            Integer integer = ClickCounterService.getInstance().incrementClickCounter();
            synchronized (sessions) {
                for (Session s : sessions) {
                    if (s.isOpen()) {
                        try {
                            s.getBasicRemote().sendText(integer.toString());
                        } catch (IOException e) {
                            logger.error("error on sending message", e);
                        }
                    } else {
                        sessions.remove(s);
                    }
                }
            }
        }
    }


    @OnClose
    public void onClose(Session session) {
        logger.info("session " + session.getId() + " has ended");
        sessions.remove(session);
    }
}
