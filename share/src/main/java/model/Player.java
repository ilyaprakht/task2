package model;


import model.enums.StepType;

public class Player {

    private String userName;
    private StepType stepType;

    public Player(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StepType getStepType() {
        return stepType;
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    @Override
    public boolean equals(Object player) {
        return ((Player) player).getUserName().equals(userName);
    }
}
