package view.event.output;


import view.enums.OutputEventType;

public class StartGameOutputEvent extends OutputEvent {

    private String player1;
    private String player2;
    private String firstStepper;

    public StartGameOutputEvent(String player1, String player2, String firstStepper) {
        super(OutputEventType.START_GAME);
        this.player1 = player1;
        this.player2 = player2;
        this.firstStepper = firstStepper;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getFirstStepper() {
        return firstStepper;
    }
}
