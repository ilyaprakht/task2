package view.impl;

import model.Field;
import view.View;
import view.event.input.EnterStepInputEvent;
import view.event.input.EnterUsernameInputEvent;
import view.event.input.InputEvent;
import view.event.output.CompetitorsStepResultOutputEvent;
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
    public void writeCompetitorsStepResultOk(OutputEvent event) {
        Field field = ((CompetitorsStepResultOutputEvent) event).getField();
        write("competitors step adopted: " + field.getStepType().getValue() + " " + field.getPosX() + " " + field.getPosY());
    }

    @Override
    public void writeCompetitorsStepResultEndOfGame(OutputEvent event) {
        Field field = ((CompetitorsStepResultOutputEvent) event).getField();
        write("competitors step adopted: " + field.getStepType().getValue() + " " + field.getPosX() + " " + field.getPosY());
        write("end of game. nobody wins");
    }

    @Override
    public void writeCompetitorsStepResultWin(OutputEvent event) {
        Field field = ((CompetitorsStepResultOutputEvent) event).getField();
        write("competitors step adopted: " + field.getStepType().getValue() + " " + field.getPosX() + " " + field.getPosY());
        write("you lose");
    }

    @Override
    public void writeStepResultOk(OutputEvent event) {
        write("step adopted");
    }

    @Override
    public void writeStepResultNotValid(OutputEvent event) {
        write("step command is out of range");
        write("please enter correct step");
    }

    @Override
    public void writeStepResultBusyField(OutputEvent event) {
        write("this field is busy yet");
        write("please enter correct step");
    }

    @Override
    public void writeStepResultEndOfGame(OutputEvent event) {
        write("end of game. nobody wins");
    }

    @Override
    public void writeStepResultWin(OutputEvent event) {
        write("you win! congratulations!");
    }

    @Override
    public void writePrintGameField(OutputEvent event) {
        PrintGameFieldOutputEvent printEvent = (PrintGameFieldOutputEvent) event;

        write("game field now is so:");
        write(printEvent.getGameField());
    }
}
