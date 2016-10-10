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

    void writeEnterStepResult(OutputEvent event);

    void writePrintGameField(OutputEvent event);

    void writeEndOfGame(OutputEvent event);

}
