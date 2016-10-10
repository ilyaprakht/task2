package model;

import model.enums.StepResult;
import model.enums.StepType;

public class GamePlay {

    private Player player1;
    private Player player2;
    private GameField gameField;

    private Player lastStepper;
    private Field lastStep;
    private boolean endOfGame = false;
    private boolean existWinner = false;

    private final byte GAME_FIELD_SIZE = 3;
    private final byte GAME_FIELD_WINNER_COUNT = 3;

    GamePlay(Player player1, Player player2) {
        this.player1 = player1;
        this.player1.setStepType(StepType.CROSS);
        this.player2 = player2;
        this.player2.setStepType(StepType.TOE);
        gameField = new GameField(GAME_FIELD_SIZE, GAME_FIELD_WINNER_COUNT);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isWinner(Player player) {
        return (existWinner && player.equals(lastStepper));
    }

    public boolean checkEndOfGame() {
        return endOfGame;
    }

    public boolean checkExistWinner() {
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

    public Field getLastStep() {
        return lastStep;
    }

    public boolean isFirstStepper(Player player) {
        return player.equals(player1);
    }

    public boolean checkNextStep(Player player) {
        return !player.equals(lastStepper);
    }

    public String getVisualGameField() {
        return gameField.getVisualGameField();
    }
}
