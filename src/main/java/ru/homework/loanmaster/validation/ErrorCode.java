package ru.homework.loanmaster.validation;

public class ErrorCode {
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static final ErrorCode SUCCESS = new ErrorCode(0, "SUCCESS");
    public static final ErrorCode INTERNAL_ERROR = new ErrorCode(1, "INTERNAL_ERROR");
    public static final ErrorCode BLOCKED = new ErrorCode(2, "BLOCKED");
    public static final ErrorCode BLOCKED_BY_COUNTRY_METRIC = new ErrorCode(3, "BLOCKED_BY_COUNTRY_METRIC");

}
