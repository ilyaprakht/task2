package view.impl;

import view.View;
import view.event.input.EnterStepInputEvent;
import view.event.input.EnterUsernameInputEvent;
import view.event.input.InputEvent;
import view.event.output.EnterStepResultOutputEvent;
import view.event.output.OutputEvent;
import view.event.output.PrintGameFieldOutputEvent;
import view.event.output.StartGameOutputEvent;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConsoleView implements View {

    @Override
    public InputEvent readUsername() {
        write("enter your username:");

        String inputLine = read();

        if (inputLine.trim().isEmpty()) {
            write("username cannot be empty or content only spaces");
            write("please try again");
            return readUsername();
        }

        return new EnterUsernameInputEvent(inputLine);
    }

    @Override
    public InputEvent readStep() {
        write("please enter your step:");

        String inputLine = read();

        Pattern p = Pattern.compile("^[1-9]+\\s[1-9]+$");
        Matcher m = p.matcher(inputLine);

        if (!m.matches()) {
            write("step command is incorrect");
            write("please enter correct step");
            return readStep();
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);
        return new EnterStepInputEvent(Byte.parseByte(tokenizer.nextToken()), Byte.parseByte(tokenizer.nextToken()));
    }

    private String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void write(String s) {
        System.out.println(s);
    }

    @Override
    public void writeStart(OutputEvent event) {
        write("welcome to game. search competitor...");
    }

    @Override
    public void writeWaitGame(OutputEvent event) {
        write("there is no free competitors. please wait");
    }

    @Override
    public void writeStartGame(OutputEvent event) {
        StartGameOutputEvent startEvent = (StartGameOutputEvent) event;

        write("competitor is defined. start the game!");
        write("players: ");
        write("X: " + startEvent.getPlayer1());
        write("0: " + startEvent.getPlayer2());
        write("first step by " + startEvent.getPlayer1());
    }

    @Override
    public void writeWaitCompetitorsStep(OutputEvent event) {
        write("competitor doesn`t step yet. wait...");
    }

    @Override
    public void writeEnterStepResult(OutputEvent event) {
        EnterStepResultOutputEvent resultEvent = (EnterStepResultOutputEvent) event;

        switch (resultEvent.getStepResult()) {
            case ENTER_OK:
                write("step adopted");
                break;
            case NOT_VALID_FIELD:
                write("step command is out of range");
                write("please enter correct step");
                break;
            case BUSY_FIELD:
                write("this field is busy yet");
                write("please enter correct step");
                break;
            case WINNER_STEP:
                write("you win! congratulations!");
                break;
            case END_OF_GAME:
                write("nobody wins");
                break;
        }
    }

    @Override
    public void writePrintGameField(OutputEvent event) {
        PrintGameFieldOutputEvent printEvent = (PrintGameFieldOutputEvent) event;

        write("game field now is so:");
        write(printEvent.getGameField());
    }

    @Override
    public void writeEndOfGame(OutputEvent event) {
        write("game is over");

        switch (event.getEventType()) {
            case END_OF_GAME:
                write("nobody wins");
                break;
            case WIN:
                write("you win! congratulations!");
                break;
            case LOSE:
                write("you lose");
        }
    }
}
