package controller;

import network.event.from_client.NetworkFromClientEvent;
import network.event.from_server.NetworkFromServerEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkController {

    private Socket socket;
    private ObjectInputStream sin;
    private ObjectOutputStream sout;

    public NetworkController(Socket socket) {
        this.socket = socket;
        try {
            sin = new ObjectInputStream(socket.getInputStream());
            sout = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEvent(NetworkFromServerEvent event) {
        try {
            sout.writeObject(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NetworkFromClientEvent getEvent() {
        NetworkFromClientEvent event = null;

        try {
            event = (NetworkFromClientEvent) sin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return event;
    }
}
