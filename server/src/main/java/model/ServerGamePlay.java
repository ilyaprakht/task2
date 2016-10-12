package model;

import model.enums.StepResult;
import model.enums.StepType;

public class ServerGamePlay {

    private Player player1;
    private Player player2;
    private GameField gameField;

    private Player lastStepper;
    private Field lastStep;
    private StepResult lastStepResult;
    private boolean readyToGame = false;
    private boolean endOfGame = false;

    private final byte GAME_FIELD_SIZE = 3;
    private final byte GAME_FIELD_WINNER_COUNT = 3;

    ServerGamePlay(Player player1) {
        this.player1 = player1;
        this.player1.setStepType(StepType.CROSS);
        gameField = new GameField(GAME_FIELD_SIZE, GAME_FIELD_WINNER_COUNT);
    }

    void addSecondPlayer(Player player) {
        player2 = player;
        player2.setStepType(StepType.TOE);
        readyToGame = true;
    }

    synchronized public Player getPlayer1() {
        return player1;
    }

    synchronized public Player getPlayer2() {
        return player2;
    }

    synchronized public boolean checkEndOfGame() {
        return endOfGame;
    }

    public StepResult makeStep(Field field, Player player) {
        StepResult result = gameField.enterStep(field);

        if (result != StepResult.NOT_VALID_FIELD && result != StepResult.BUSY_FIELD) {
            lastStepper = player;
            lastStep = field;
            lastStepResult = result;
        }

        if (result == StepResult.END_OF_GAME) {
            endOfGame = true;
        }

        if (result == StepResult.WINNER_STEP) {
            endOfGame = true;
        }

        return result;
    }

    synchronized public Field getLastStep() {
        return lastStep;
    }

    synchronized public StepResult getLastStepResult() {
        return lastStepResult;
    }

    synchronized public boolean checkNextStep(Player player) {
        if (lastStepper == null) {
            return false;
        }
        return !player.equals(lastStepper);
    }

    synchronized public String getVisualGameField() {
        return gameField.getVisualGameField();
    }

    synchronized public boolean checkReadyToGame() {
        return readyToGame;
    }

    @Override
    public boolean equals(Object gamePlay) {
        return (player1.equals(((ServerGamePlay) gamePlay).getPlayer1()) && player2.equals(((ServerGamePlay) gamePlay).getPlayer2()));
    }

}
