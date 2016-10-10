package network.event.from_server;


import model.enums.StepResult;
import network.enums.NetworkFromServerEventType;

public class StepResultNetworkFromServerEvent extends NetworkFromServerEvent {

    private StepResult stepResult;

    public StepResultNetworkFromServerEvent(StepResult stepResult) {
        super(NetworkFromServerEventType.STEP_RESULT);
        this.stepResult = stepResult;
    }

    public StepResult getStepResult() {
        return stepResult;
    }
}
