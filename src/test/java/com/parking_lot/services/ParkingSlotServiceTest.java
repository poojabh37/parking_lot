package com.parking_lot.services;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class ParkingSlotServiceTest {

    private static final String REGISTRATION_NUMBER = "CH-01-AF-6705";
    private static final int PARKING_LOT_CAPACITY = 3;
    private ParkingSlotService parkingSlotService = ParkingSlotService.getInstance();
    private ParkingLotService parkingLotService = ParkingLotService.getInstance();

    @Before
    public void init() {
        parkingLotService.createParkingLot(PARKING_LOT_CAPACITY);
    }

    @Test
    public void test_assign_first_parkingSlot_Successful() {
        //when
        parkingSlotService.assignParkingLot(REGISTRATION_NUMBER);
        //then
        ParkingSlot parkingSlot = ParkingLot.getInstance().getParkingSlots().get(0);
        Assert.assertTrue(parkingSlot.isOccupied());
        Assert.assertEquals(REGISTRATION_NUMBER, parkingSlot.getRegistrationNumber());
    }


    @Test
    public void test_assign_parkingSlot_no_Space_available() {
        //given
        assignAllSlots();
        //when
        parkingSlotService.assignParkingLot(REGISTRATION_NUMBER);
        //then
        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
        Assert.assertFalse(parkingSlots
                .stream()
                .anyMatch(slot -> slot.getRegistrationNumber().equals(REGISTRATION_NUMBER)));
    }

    private void assignAllSlots() {
        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
        for (ParkingSlot slot : parkingSlots) {
            slot.setOccupied(true);
            slot.setRegistrationNumber(Mockito.anyString());
        }
    }

}
