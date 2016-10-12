package controller;

import org.apache.log4j.Logger;
import view.View;
import view.ViewObserver;
import view.enums.InputEventType;
import view.event.input.InputEvent;
import view.event.output.CompetitorsStepResultOutputEvent;
import view.event.output.OutputEvent;
import view.event.output.StepResultOutputEvent;

public class ViewController implements ViewObserver{

    private final static Logger LOG = Logger.getLogger("debug");

    private View view;

    public ViewController(View view) {
        this.view = view;
    }

    InputEvent getFromView(InputEventType eventType) {
        InputEvent event = null;
        switch (eventType) {
            case ENTER_USERNAME:
                event = view.readUsername();
                LOG.debug(event);
                break;
            case ENTER_STEP:
                event = view.readStep();
                LOG.debug(event);
                break;
        }
        return event;
    }

    private void sendToView(OutputEvent event) {
        LOG.debug(event);
        switch (event.getEventType()) {
            case START:
                view.writeStart(event);
                break;
            case WAIT_GAME:
                view.writeWaitGame(event);
                break;
            case START_GAME:
                view.writeStartGame(event);
                break;
            case WAIT_COMPETITORS_STEP:
                view.writeWaitCompetitorsStep(event);
                break;
            case STEP_RESULT:
                switch (((StepResultOutputEvent) event).getStepResult()) {
                    case NOT_VALID_FIELD:
                        view.writeStepResultNotValid(event);
                        break;
                    case BUSY_FIELD:
                        view.writeStepResultBusyField(event);
                        break;
                    case ENTER_OK:
                        view.writeStepResultOk(event);
                        break;
                    case WINNER_STEP:
                        view.writeStepResultWin(event);
                        break;
                    case END_OF_GAME:
                        view.writeStepResultEndOfGame(event);
                        break;
                }
                break;
            case COMPETITORS_STEP_RESULT:
                switch (((CompetitorsStepResultOutputEvent) event).getStepResult()) {
                    case ENTER_OK:
                        view.writeCompetitorsStepResultOk(event);
                        break;
                    case WINNER_STEP:
                        view.writeCompetitorsStepResultWin(event);
                        break;
                    case END_OF_GAME:
                        view.writeCompetitorsStepResultEndOfGame(event);
                }
                break;
            case PRINT_GAME_FIELD:
                view.writePrintGameField(event);
        }
    }

    @Override
    public void handleEvent(OutputEvent event) {
        sendToView(event);
    }
}
