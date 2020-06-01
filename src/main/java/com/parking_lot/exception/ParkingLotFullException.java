package com.parking_lot.exception;

import com.parking_lot.command.ErrorConstants;

public class ParkingLotFullException extends RuntimeException {

    private String message;

    public ParkingLotFullException() {
        this.message = ErrorConstants.PARKING_LOT_FULL_ERROR_MESSAGE;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
