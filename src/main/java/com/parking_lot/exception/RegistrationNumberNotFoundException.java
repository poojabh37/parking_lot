package com.parking_lot.exception;

import static com.parking_lot.command.ErrorConstants.REGISTRATION_NO_NOT_FOUND_ERROR_MESSAGE;

public class RegistrationNumberNotFoundException extends RuntimeException {

    private String message;

    public RegistrationNumberNotFoundException() {
        this.message = REGISTRATION_NO_NOT_FOUND_ERROR_MESSAGE;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
