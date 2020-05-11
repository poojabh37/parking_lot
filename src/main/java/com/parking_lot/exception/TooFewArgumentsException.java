package com.parking_lot.exception;

public class TooFewArgumentsException extends RuntimeException {

    public TooFewArgumentsException() {
        super("Argument File Name is missing");
    }
}