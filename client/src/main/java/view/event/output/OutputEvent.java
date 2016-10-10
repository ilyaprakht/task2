package view.event.output;

import view.enums.OutputEventType;


public class OutputEvent {

    private OutputEventType eventType;

    public OutputEvent(OutputEventType eventType) {
        this.eventType = eventType;
    }

    public OutputEventType getEventType() {
        return eventType;
    }
}
