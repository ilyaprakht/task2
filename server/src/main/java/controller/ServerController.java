package controller;


import model.ServerGamePlay;
import network.event.from_client.NetworkFromClientEvent;

public class ServerController implements Runnable {

    private NetworkController network;
    private ServerGamePlay gamePlay;

    public ServerController(NetworkController network) {
        this.network = network;
    }

    @Override
    public void run() {
        while (true) {
            NetworkFromClientEvent clientEvent = network.getEvent();

            switch(clientEvent.getEventType()) {
                case READY_TO_PLAY:
                    handleReadyToPlay();
                    break;
                case CHECK_GAME:
                    handleCheckGame();
                    break;
                case SEND_STEP:
                    handleSendStep();
                    break;
                case CHECK_COMPETITORS_STEP:
                    handleCheckCompetitorsStep();
                    break;
            }
        }
    }

    private void handleReadyToPlay() {

    }

    private void handleCheckGame() {

    }

    private void handleSendStep() {

    }

    private void handleCheckCompetitorsStep() {

    }
}
