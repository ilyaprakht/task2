package controller;


import model.ServerGamePlay;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionController implements Runnable {

    private int port;

    public ConnectionController(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();

                NetworkController networkController = new NetworkController(socket);
                ServerController controller = new ServerController(networkController);

                Thread thread = new Thread(controller);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
