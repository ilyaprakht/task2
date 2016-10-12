package model.enums;


public enum StepType {
    CROSS("X"),
    TOE("0"),
    ;

    private String value;

    StepType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
