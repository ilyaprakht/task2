package network.event.from_server;


import model.Field;
import model.enums.StepResult;
import network.enums.NetworkFromServerEventType;

public class CompetitorsStepResultNetworkFromServerEvent extends NetworkFromServerEvent {

    private StepResult stepResult;
    private Field field;

    public CompetitorsStepResultNetworkFromServerEvent(StepResult stepResult, Field field) {
        super(NetworkFromServerEventType.COMPETITORS_STEP);
        this.stepResult = stepResult;
        this.field = field;
    }

    public StepResult getStepResult() {
        return stepResult;
    }

    public Field getField() {
        return field;
    }
}
