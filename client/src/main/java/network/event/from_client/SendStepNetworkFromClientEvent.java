package network.event.from_client;


import model.Field;
import network.enums.NetworkFromClientEventType;

public class SendStepNetworkFromClientEvent extends NetworkFromClientEvent {

    private Field field;

    public SendStepNetworkFromClientEvent(Field field) {
        super(NetworkFromClientEventType.SEND_STEP);
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}
