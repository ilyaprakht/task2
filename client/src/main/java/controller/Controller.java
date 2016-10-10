package controller;

import model.Field;
import model.Player;
import network.NetworkController;
import network.enums.NetworkFromClientEventType;
import network.enums.NetworkFromServerEventType;
import network.event.from_client.NetworkFromClientEvent;
import network.event.from_client.ReadyToPlayNetworkFromClientEvent;
import network.event.from_client.SendStepNetworkFromClientEvent;
import network.event.from_server.NetworkFromServerEvent;
import network.event.from_server.StartGameNetworkFromServerEvent;
import network.event.from_server.StepResultNetworkFromServerEvent;
import view.View;
import view.enums.OutputEventType;
import view.event.input.EnterStepInputEvent;
import view.event.input.EnterUsernameInputEvent;
import view.event.output.OutputEvent;
import view.event.output.StartGameOutputEvent;
import view.event.output.StepResultOutputEvent;

public class Controller implements Runnable {

    private View view;
    private NetworkController network;
    private Player player;
    private Player competitorsPlayer;
    private boolean isFirstStepper;

    private final long SLEEP_TIME = 2000;

    public Controller(View view, NetworkController network) {
        this.view = view;
        this.network = network;
    }

    @Override
    public void run() {
        start();
        enterUsername();
        sendReadyToPlay();
        checkStartGame();
        if (isFirstStepper) {
            enterStep();
            checkStepResult();
        }
    }

    private void start() {
        view.writeStart(new OutputEvent(OutputEventType.START));
    }

    private void enterUsername() {
        EnterUsernameInputEvent event = (EnterUsernameInputEvent) view.readUsername();
        player = new Player(event.getUserName());
    }

    private void sendReadyToPlay() {
        ReadyToPlayNetworkFromClientEvent event = new ReadyToPlayNetworkFromClientEvent(player);
        network.sendEvent(event);
    }

    private void checkStartGame() {
        NetworkFromServerEvent event = network.getEvent();
        if (event.getEventType() == NetworkFromServerEventType.WAIT_GAME) {
            waitGame();
            checkStartGame();
        } else if (event.getEventType() == NetworkFromServerEventType.START_GAME) {
            startGame(event);
        }
    }

    private void waitGame() {
        view.writeWaitGame(new OutputEvent(OutputEventType.WAIT_GAME));
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        network.sendEvent(new NetworkFromClientEvent(NetworkFromClientEventType.CHECK_GAME));
    }

    private void startGame(NetworkFromServerEvent event) {
        StartGameNetworkFromServerEvent startEvent = (StartGameNetworkFromServerEvent) event;

        if (startEvent.getPlayer1().getUserName().equals(player.getUserName())) {
            player = startEvent.getPlayer1();
            competitorsPlayer = startEvent.getPlayer2();
            isFirstStepper = true;

        } else if (startEvent.getPlayer2().getUserName().equals(player.getUserName())) {
            player = startEvent.getPlayer2();
            competitorsPlayer = startEvent.getPlayer1();
            isFirstStepper = false;
        }

        StartGameOutputEvent outEvent = new StartGameOutputEvent(startEvent.getPlayer1().getUserName(),
                startEvent.getPlayer2().getUserName());
        view.writeStartGame(outEvent);
    }

    private void enterStep() {
        EnterStepInputEvent event = (EnterStepInputEvent) view.readStep();
        Field field = new Field(event.getPosX(), event.getPosY(), player);
        network.sendEvent(new SendStepNetworkFromClientEvent(field));
    }

    private void checkStepResult() {
        StepResultNetworkFromServerEvent event = (StepResultNetworkFromServerEvent) network.getEvent();
        view.writeEnterStepResult(new StepResultOutputEvent(event.getStepResult()));
    }
}
