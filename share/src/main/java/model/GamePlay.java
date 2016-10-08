package model;

import model.enums.StepResult;

public interface GamePlay {
    Player getPlayer1();

    Player getPlayer2();

    boolean isWinner(Player player);

    boolean checkEndOfGame();

    boolean checkExistWinner();

    StepResult makeStep(Field field, Player player);

    Field getLastStep();

    boolean isFirstStepper(Player player);

    boolean checkNextStep(Player player);

    String getVisualGameField();
}
