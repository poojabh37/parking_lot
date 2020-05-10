package com.parking_lot.services;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.parking_lot.services.Constants.PARKING_LOT_CAPACITY;

public class ParkingChargeCalculatorTest {

    private ParkingLotService parkingLotService = ParkingLotService.getInstance();

    @Before
    public void init() {
        parkingLotService.createParkingLot(PARKING_LOT_CAPACITY);
        assignAllSlots();
    }

    private void assignAllSlots() {
        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
        for (ParkingSlot slot : parkingSlots) {
            slot.setOccupied(true);
            slot.setRegistrationNumber(Mockito.anyString());
        }
    }

    @Test
    public void test_charge_calculated__two_hours() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingSlot slot = parkingLot.getParkingSlots().get(1);

        int charge = ParkingChargeCalculator.calculateCharge(2);

        Assert.assertEquals(10, charge);
    }

    @Test
    public void test_charge_calculated__more_than_two_hours() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingSlot slot = parkingLot.getParkingSlots().get(1);

        int charge = ParkingChargeCalculator.calculateCharge(6);

        Assert.assertEquals(50, charge);
    }


}
