package model;

import model.enums.StepType;
import java.io.Serializable;


public class Player implements Serializable {

    private String userName;
    private StepType stepType;

    Player(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    StepType getStepType() {
        return stepType;
    }

    void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    @Override
    public boolean equals(Object player) {
        return userName.equals(((Player) player).getUserName());
    }
}
