package com.parking_lot.services;

public class ParkingChargeCalculator {

    private static final int CHARGE = 10;
    private static final int MINIMUM_HOURS = 2;

    public static int calculateCharge(int hours) {
        if (hours <= MINIMUM_HOURS) {
            return CHARGE;
        }
        return CHARGE + (hours - MINIMUM_HOURS) * CHARGE;
    }
}
