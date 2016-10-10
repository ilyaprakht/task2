package view.event.output;

import model.enums.StepResult;
import view.enums.OutputEventType;


public class StepResultOutputEvent extends OutputEvent {

    private StepResult stepResult;

    public StepResultOutputEvent(StepResult stepResult) {
        super(OutputEventType.STEP_RESULT);
        this.stepResult = stepResult;
    }

    public StepResult getStepResult() {
        return stepResult;
    }
}
