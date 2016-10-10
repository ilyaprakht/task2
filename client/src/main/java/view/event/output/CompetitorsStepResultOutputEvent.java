package view.event.output;


import model.Field;
import model.enums.StepResult;
import view.enums.OutputEventType;

public class CompetitorsStepResultOutputEvent extends OutputEvent {

    private StepResult stepResult;
    private Field field;

    public CompetitorsStepResultOutputEvent(StepResult stepResult, Field field) {
        super(OutputEventType.COMPETITORS_STEP_RESULT);
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
