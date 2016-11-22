package ru.homework.loanmaster.rest.dto;

import ru.homework.loanmaster.validation.ErrorCode;

public class ResultDto {
    public final static ResultDto SUCCESS = new ResultDto(ErrorCode.SUCCESS);

    private int errorCode;
    private String errorMessage;

    public ResultDto(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ResultDto(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }

    public ResultDto(ErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
