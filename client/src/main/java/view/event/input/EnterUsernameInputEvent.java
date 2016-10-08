package view.event.input;


import view.enums.InputEventType;

public class EnterUsernameInputEvent extends InputEvent {

    private String userName;

    public EnterUsernameInputEvent(String userName) {
        super(InputEventType.ENTER_USERNAME);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
