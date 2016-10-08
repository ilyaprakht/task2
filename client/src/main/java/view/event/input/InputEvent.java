package view.event.input;


import view.enums.InputEventType;

public class InputEvent {

    private InputEventType eventType;

    InputEvent(InputEventType eventType) {
        this.eventType = eventType;
    }

    public InputEventType getEventType() {
        return eventType;
    }
}
