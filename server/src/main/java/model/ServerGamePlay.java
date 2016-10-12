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
    private boolean existWinner = false;

    private final byte GAME_FIELD_SIZE = 3;
    private final byte GAME_FIELD_WINNER_COUNT = 3;

    ServerGamePlay(Player player1) {
        this.player1 = player1;
        this.player1.setStepType(StepType.CROSS);
        gameField = new GameField(GAME_FIELD_SIZE, GAME_FIELD_WINNER_COUNT);
    }

    ServerGamePlay(Player player1, Player player2) {
        this.player1 = player1;
        this.player1.setStepType(StepType.CROSS);
        this.player2 = player2;
        this.player2.setStepType(StepType.TOE);
        gameField = new GameField(GAME_FIELD_SIZE, GAME_FIELD_WINNER_COUNT);
        readyToGame = true;
    }

    public void addSecondPlayer(Player player) {
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

    synchronized public boolean isWinner(Player player) {
        return (existWinner && player.equals(lastStepper));
    }

    synchronized public boolean checkEndOfGame() {
        return endOfGame;
    }

    synchronized public boolean checkExistWinner() {
        return existWinner;
    }

    public StepResult makeStep(Field field, Player player) {
        if (!checkNextStep(player)) {
            return null;
        }

        StepResult result = gameField.enterStep(field);

        if (result != StepResult.NOT_VALID_FIELD && result != StepResult.BUSY_FIELD) {
            lastStepper = player;
            lastStep = field;
        }

        if (result == StepResult.END_OF_GAME) {
            endOfGame = true;
        }

        if (result == StepResult.WINNER_STEP) {
            endOfGame = true;
            existWinner = true;
        }

        return result;
    }

    synchronized public Field getLastStep() {
        return lastStep;
    }

    synchronized public StepResult getLastStepResult() {
        return lastStepResult;
    }

    synchronized public boolean isFirstStepper(Player player) {
        return player.equals(player1);
    }

    synchronized public boolean checkNextStep(Player player) {
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
