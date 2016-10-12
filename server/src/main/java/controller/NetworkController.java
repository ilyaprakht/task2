package controller;

import network.event.from_client.NetworkFromClientEvent;
import network.event.from_server.NetworkFromServerEvent;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;


public class NetworkController {

    private final static Logger LOG = Logger.getLogger("debug");

    private ObjectInputStream sin;
    private ObjectOutputStream sout;

    NetworkController(Socket socket) {
        try {
            sout = new ObjectOutputStream(socket.getOutputStream());
            sout.flush();
            sin = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            LOG.error(e.getCause() + " " + e.getMessage());
        }
    }

    void sendEvent(NetworkFromServerEvent event) {
        try {
            sout.writeObject(event);
            LOG.debug(event);
        } catch (Exception e) {
            LOG.error(e.getCause() + " " + e.getMessage());
        }
    }

    NetworkFromClientEvent getEvent() {
        NetworkFromClientEvent event = null;

        try {
            event = (NetworkFromClientEvent) sin.readObject();
            LOG.debug(event);
        } catch (Exception e) {
            LOG.error(e.getCause() + " " + e.getMessage());
        }

        return event;
    }
}
