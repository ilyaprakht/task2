package view.event.output;


import view.enums.OutputEventType;

public class PrintGameFieldOutputEvent extends OutputEvent {

    private String gameField;

    public PrintGameFieldOutputEvent(String gameField) {
        super(OutputEventType.PRINT_GAME_FIELD);
        this.gameField = gameField;
    }

    public String getGameField() {
        return gameField;
    }
}
