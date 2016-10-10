package controller;


import network.event.from_client.NetworkFromClientEvent;
import network.event.from_server.NetworkFromServerEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkController {

    private InetAddress address;
    private int port;
    private Socket socket;
    private ObjectInputStream sin;
    private ObjectOutputStream sout;

    public NetworkController(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    private void connect() {
        try {
            socket = new Socket(address, port);
            sin = new ObjectInputStream(socket.getInputStream());
            sout = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pingOrReconnect() {
        if (!socket.isConnected()) {
            connect();
        }
    }

    public void sendEvent(NetworkFromClientEvent event) {
        pingOrReconnect();

        try {
            sout.writeObject(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NetworkFromServerEvent getEvent() {
        pingOrReconnect();

        NetworkFromServerEvent event = null;

        try {
            event = (NetworkFromServerEvent) sin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return event;
    }
}
