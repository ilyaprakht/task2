package model;

import model.enums.StepResult;

public class ClientGamePlay implements GamePlay {

    private GamePlay serverGamePlay; //TEMPORARY for test without network

    ClientGamePlay(GamePlay serverGamePlay) {
        this.serverGamePlay = serverGamePlay;
    }

    @Override
    public Player getPlayer1() {
        return serverGamePlay.getPlayer1();
    }

    @Override
    public Player getPlayer2() {
        return serverGamePlay.getPlayer2();
    }

    @Override
    public boolean isWinner(Player player) {
        return serverGamePlay.isWinner(player);
    }

    @Override
    public boolean checkEndOfGame() {
        return serverGamePlay.checkEndOfGame();
    }

    @Override
    public boolean checkExistWinner() {
        return serverGamePlay.checkExistWinner();
    }

    @Override
    public StepResult makeStep(Field field, Player player) {
        return serverGamePlay.makeStep(field, player);
    }

    @Override
    public Field getLastStep() {
        return serverGamePlay.getLastStep();
    }

    @Override
    public boolean isFirstStepper(Player player) {
        return serverGamePlay.isFirstStepper(player);
    }

    @Override
    public boolean checkNextStep(Player player) {
        return serverGamePlay.checkNextStep(player);
    }

    @Override
    public String getVisualGameField() {
        return serverGamePlay.getVisualGameField();
    }
}
