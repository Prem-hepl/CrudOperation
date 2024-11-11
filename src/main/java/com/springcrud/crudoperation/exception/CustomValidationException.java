package com.springcrud.crudoperation.exception;


public class CustomValidationException extends Exception{

   private final ErrorCode errorCode;


    public CustomValidationException(ErrorCode errorCode) {
        super(getMessage(errorCode));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private static String getMessage(ErrorCode errorCode) {
        if (errorCode.getErrorMessage() != null) {
            return errorCode.getErrorMessage();
        }else {
            return null;
        }
    }
}
