package com.parking_lot.exception;

import static com.parking_lot.command.ErrorConstants.ARGUMENT_FILE_NAME_MISSING;

public class TooFewArgumentsException extends RuntimeException {

    private String message;

    public TooFewArgumentsException() {
        this.message = ARGUMENT_FILE_NAME_MISSING;
    }

    @Override
    public String getMessage() {
        return message;
    }
}