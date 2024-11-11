package com.springcrud.crudoperation.exception;


public enum ErrorCode implements ErrorHandle{

    CAP_1001("1001", "User not found exception");

    private String ErrorCode;
    private String ErrorMessage;

    ErrorCode(String errorCode, String errorMessage){
        this.ErrorCode=errorCode;
        this.ErrorMessage=errorMessage;
    }

    @Override
    public String getErrorCode() {
        return this.ErrorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.ErrorMessage;
    }
}
