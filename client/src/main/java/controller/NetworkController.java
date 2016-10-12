package controller;


import network.event.from_client.NetworkFromClientEvent;
import network.event.from_server.NetworkFromServerEvent;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkController {

    private final static Logger LOG = Logger.getLogger("debug");

    private String address;
    private int port;
    private Socket socket;
    private ObjectInputStream sin;
    private ObjectOutputStream sout;

    public NetworkController(String address, int port) {
        this.address = address;
        this.port = port;
    }

    private void connect() {
        try {
            InetAddress inetAdr = InetAddress.getByName(address);
            socket = new Socket(inetAdr, port);
            sout = new ObjectOutputStream(socket.getOutputStream());
            sout.flush();
            sin = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            LOG.error(e.getCause() + " " + e.getMessage());
        }
    }

    private void pingOrReconnect() {
        if (socket == null || !socket.isConnected()) {
            connect();
        }
    }

    void sendEvent(NetworkFromClientEvent event) {
        pingOrReconnect();

        try {
            sout.writeObject(event);
            LOG.debug(event);
        } catch (Exception e) {
            LOG.error(e.getCause() + " " + e.getMessage());
        }
    }

    public NetworkFromServerEvent getEvent() {
        pingOrReconnect();

        NetworkFromServerEvent event = null;

        try {
            event = (NetworkFromServerEvent) sin.readObject();
            LOG.debug(event);
        } catch (Exception e) {
            LOG.error(e.getCause() + " " + e.getMessage());
        }

        return event;
    }
}
