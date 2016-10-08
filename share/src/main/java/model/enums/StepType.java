package model.enums;


public enum StepType {
    CROSS((byte) 1, "X"),
    TOE((byte) 2, "0"),
    ;

    private byte code;
    private String value;

    StepType(byte code, String value) {
        this.code = code;
        this.value = value;
    }

    public byte getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
