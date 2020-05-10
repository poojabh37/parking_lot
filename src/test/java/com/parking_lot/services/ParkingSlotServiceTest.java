package com.parking_lot.services;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;
import org.junit.Assert;
import org.junit.Test;

public class ParkingSlotServiceTest {

    private static final String REGISTRATION_NUMBER = "CH-01-AF-6705";
    private ParkingSlotService parkingSlotService = ParkingSlotService.getInstance();
    private ParkingLotService parkingLotService = ParkingLotService.getInstance();

    @Test
    public void test_assign_first_parkingSlot_Successful() {
        //given
        parkingLotService.createParkingLot(3);
        //when
        parkingSlotService.assignParkingLot(REGISTRATION_NUMBER);
        //then
        ParkingSlot parkingSlot = ParkingLot.getInstance().getParkingSlots().get(0);
        Assert.assertTrue(parkingSlot.isOccupied());
        Assert.assertEquals(REGISTRATION_NUMBER, parkingSlot.getRegistrationNumber());

    }

}
