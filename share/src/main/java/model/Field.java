package model;


import model.enums.StepType;

public class Field {

    private byte posX;
    private byte posY;
    private StepType stepType;

    private Field(byte posX, byte posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Field(byte posX, byte posY, StepType stepType) {
        this(posX, posY);
        this.stepType = stepType;
    }

    public Field(byte posX, byte posY, Player player) {
        this(posX, posY);
        this.stepType = player.getStepType();
    }

    public byte getPosX() {
        return posX;
    }

    public byte getPosY() {
        return posY;
    }

    public StepType getStepType() {
        return stepType;
    }
}
