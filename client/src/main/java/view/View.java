package view;

import view.event.input.InputEvent;
import view.event.output.OutputEvent;

public interface View {

    InputEvent readUsername();

    InputEvent readStep();

    void writeStart(OutputEvent event);

    void writeWaitGame(OutputEvent event);

    void writeStartGame(OutputEvent event);

    void writeWaitCompetitorsStep(OutputEvent event);

    void writeCompetitorsStepResultOk(OutputEvent event);

    void writeCompetitorsStepResultEndOfGame(OutputEvent event);

    void writeCompetitorsStepResultWin(OutputEvent event);

    void writeStepResultOk(OutputEvent event);

    void writeStepResultNotValid(OutputEvent event);

    void writeStepResultBusyField(OutputEvent event);

    void writeStepResultEndOfGame(OutputEvent event);

    void writeStepResultWin(OutputEvent event);

    void writePrintGameField(OutputEvent event);

}
