package model;

import model.enums.StepType;
import java.io.Serializable;


public class Field implements Serializable {

    private byte posX;
    private byte posY;
    private StepType stepType;

    private Field(byte posX, byte posY) {
        this.posX = posX;
        this.posY = posY;
    }

    private Field(byte posX, byte posY, StepType stepType) {
        this(posX, posY);
        this.stepType = stepType;
    }

    public Field(byte posX, byte posY, Player player) {
        this(posX, posY, player.getStepType());
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
