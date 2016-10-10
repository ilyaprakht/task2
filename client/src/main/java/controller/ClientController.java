package controller;

import model.ClientGamePlay;
import model.Field;
import network.enums.NetworkFromClientEventType;
import network.enums.NetworkFromServerEventType;
import network.event.from_client.NetworkFromClientEvent;
import network.event.from_client.ReadyToPlayNetworkFromClientEvent;
import network.event.from_client.SendStepNetworkFromClientEvent;
import network.event.from_server.NetworkFromServerEvent;
import network.event.from_server.StartGameNetworkFromServerEvent;
import network.event.from_server.StepResultNetworkFromServerEvent;
import view.ViewObservable;
import view.ViewObserver;
import view.enums.InputEventType;
import view.enums.OutputEventType;
import view.event.input.EnterStepInputEvent;
import view.event.input.EnterUsernameInputEvent;
import view.event.output.OutputEvent;
import view.event.output.StartGameOutputEvent;
import view.event.output.StepResultOutputEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientController implements Runnable, ViewObservable {

    ViewController view;
    private NetworkController network;
    List<ViewObserver> listObservers;
    ClientGamePlay gamePlay;

    private final long SLEEP_TIME = 2000;

    public ClientController(ViewController view, NetworkController network) {
        this.view = view;
        addObserver(this.view);
        this.network = network;
    }

    @Override
    public void run() {
        start();
        enterUsername();
        sendReadyToPlay();
        checkStartGame();
        if (gamePlay.isFirstStepper()) {
            enterStep();
            checkStepResult();
        }
    }

    private void start() {
        notifyObservers(new OutputEvent(OutputEventType.START));
    }

    private void enterUsername() {
        EnterUsernameInputEvent event = (EnterUsernameInputEvent) view.getFromView(InputEventType.ENTER_USERNAME);
        gamePlay = new ClientGamePlay(event.getUserName());
    }

    private void sendReadyToPlay() {
        ReadyToPlayNetworkFromClientEvent event = new ReadyToPlayNetworkFromClientEvent(gamePlay.getPlayer());
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
        notifyObservers(new OutputEvent(OutputEventType.WAIT_GAME));
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        network.sendEvent(new NetworkFromClientEvent(NetworkFromClientEventType.CHECK_GAME));
    }

    private void startGame(NetworkFromServerEvent event) {
        StartGameNetworkFromServerEvent startEvent = (StartGameNetworkFromServerEvent) event;

        if (startEvent.getPlayer1().getUserName().equals(gamePlay.getPlayer().getUserName())) {
            gamePlay.setPlayer(startEvent.getPlayer1());
            gamePlay.setCompetitorsPlayer(startEvent.getPlayer2());
            gamePlay.setFirstStepper(true);

        } else if (startEvent.getPlayer2().getUserName().equals(gamePlay.getPlayer().getUserName())) {
            gamePlay.setPlayer(startEvent.getPlayer2());
            gamePlay.setCompetitorsPlayer(startEvent.getPlayer1());
            gamePlay.setFirstStepper(false);
        }

        StartGameOutputEvent outEvent = new StartGameOutputEvent(startEvent.getPlayer1().getUserName(),
                startEvent.getPlayer2().getUserName());
        notifyObservers(outEvent);
    }

    private void enterStep() {
        EnterStepInputEvent event = (EnterStepInputEvent) view.getFromView(InputEventType.ENTER_STEP);
        Field field = new Field(event.getPosX(), event.getPosY(), gamePlay.getPlayer());
        network.sendEvent(new SendStepNetworkFromClientEvent(field));
    }

    private void checkStepResult() {
        StepResultNetworkFromServerEvent event = (StepResultNetworkFromServerEvent) network.getEvent();
        StepResultOutputEvent outEvent = new StepResultOutputEvent(event.getStepResult());
        notifyObservers(outEvent);
    }

    @Override
    public void addObserver(ViewObserver observer) {
        if (listObservers == null) {
            listObservers = new ArrayList<>();
        }
        listObservers.add(observer);
    }

    @Override
    public void removeObserver(ViewObserver observer) {
        listObservers.remove(observer);
    }

    @Override
    public void notifyObservers(OutputEvent event) {
        for (ViewObserver observer : listObservers) {
            observer.handleEvent(event);
        }
    }
}
