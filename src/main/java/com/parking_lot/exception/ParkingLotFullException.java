package com.parking_lot.exception;

import static com.parking_lot.command.ErrorConstants.PARKING_LOT_FULL_ERROR_MESSAGE;

public class ParkingLotFullException extends RuntimeException {

    private String message;

    public ParkingLotFullException() {
        this.message = PARKING_LOT_FULL_ERROR_MESSAGE;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
