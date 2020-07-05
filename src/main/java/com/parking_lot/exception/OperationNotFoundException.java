package com.parking_lot.exception;

import static com.parking_lot.command.ErrorConstants.OPERATION_NOT_FOUND_ERROR_MESSAGE;

public class OperationNotFoundException extends IllegalArgumentException {

    private String message;

    public OperationNotFoundException() {
        this.message = OPERATION_NOT_FOUND_ERROR_MESSAGE;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
