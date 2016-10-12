package controller;


import model.Player;
import model.ServerGamePlay;
import model.ServerGamePlayPool;
import model.enums.StepResult;
import network.enums.NetworkFromServerEventType;
import network.event.from_client.NetworkFromClientEvent;
import network.event.from_client.ReadyToPlayNetworkFromClientEvent;
import network.event.from_client.SendStepNetworkFromClientEvent;
import network.event.from_server.CompetitorsStepResultNetworkFromServerEvent;
import network.event.from_server.NetworkFromServerEvent;
import network.event.from_server.StartGameNetworkFromServerEvent;
import network.event.from_server.StepResultNetworkFromServerEvent;

public class ServerController implements Runnable {

    private NetworkController network;
    private ServerGamePlay gamePlay;
    private Player player;

    public ServerController(NetworkController network) {
        this.network = network;
    }

    @Override
    public void run() {
        while (true) {
            NetworkFromClientEvent clientEvent = network.getEvent();

            switch(clientEvent.getEventType()) {
                case READY_TO_PLAY:
                    handleReadyToPlay(clientEvent);
                    break;
                case CHECK_GAME:
                    handleCheckGame();
                    break;
                case SEND_STEP:
                    handleSendStep(clientEvent);
                    break;
                case CHECK_COMPETITORS_STEP:
                    handleCheckCompetitorsStep();
                    break;
            }

            if (gamePlay.checkEndOfGame()) {
                ServerGamePlayPool.getInstance().removeGamePlayFromPool(gamePlay);
                break;
            }
        }
    }

    private void handleReadyToPlay(NetworkFromClientEvent clientEvent) {
        ReadyToPlayNetworkFromClientEvent event = (ReadyToPlayNetworkFromClientEvent) clientEvent;
        player = event.getPlayer();
        gamePlay = ServerGamePlayPool.getInstance().findFreeGamePlay(player);
    }

    private void handleCheckGame() {
        if (gamePlay.checkReadyToGame()) {
            NetworkFromServerEvent event = new StartGameNetworkFromServerEvent(gamePlay.getPlayer1(), gamePlay.getPlayer2());
            network.sendEvent(event);
        } else {
            network.sendEvent(new NetworkFromServerEvent(NetworkFromServerEventType.WAIT_GAME));
        }
    }

    private void handleSendStep(NetworkFromClientEvent clientEvent) {
        SendStepNetworkFromClientEvent event = (SendStepNetworkFromClientEvent) clientEvent;
        StepResult stepResult = gamePlay.makeStep(event.getField(), player);
        NetworkFromServerEvent serverEvent = new StepResultNetworkFromServerEvent(stepResult);
        network.sendEvent(serverEvent);
    }

    private void handleCheckCompetitorsStep() {
        if (gamePlay.checkNextStep(player)) {
            NetworkFromServerEvent event = new CompetitorsStepResultNetworkFromServerEvent(gamePlay.getLastStepResult(), gamePlay.getLastStep());
            network.sendEvent(event);
        } else {
            network.sendEvent(new NetworkFromServerEvent(NetworkFromServerEventType.WAIT_COMPETITORS_STEP));
        }
    }
}
