package view.event.input;


import view.enums.InputEventType;

public class EnterStepInputEvent extends InputEvent {

    private byte posX;
    private byte posY;

    public EnterStepInputEvent(byte posX, byte posY) {
        super(InputEventType.ENTER_STEP);
        this.posX = posX;
        this.posY = posY;
    }

    public byte getPosX() {
        return posX;
    }

    public byte getPosY() {
        return posY;
    }
}
