package com.parking_lot.services;

import org.junit.Assert;
import org.junit.Test;

public class ParkingChargeCalculatorTest {

    @Test
    public void test_charge_calculated__two_hours() {
        int charge = ParkingChargeCalculator.calculateCharge(2);

        Assert.assertEquals(10, charge);
    }

    @Test
    public void test_charge_calculated__more_than_two_hours() {
        int charge = ParkingChargeCalculator.calculateCharge(6);

        Assert.assertEquals(50, charge);
    }

}
