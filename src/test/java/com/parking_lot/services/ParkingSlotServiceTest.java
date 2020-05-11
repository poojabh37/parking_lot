package com.parking_lot.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import static com.parking_lot.services.Constants.PARKING_LOT_CAPACITY;
import static com.parking_lot.services.Constants.REGISTRATION_NUMBER;

public class ParkingSlotServiceTest {

    private ParkingSlotService parkingSlotService = ParkingSlotService.getInstance();
    private ParkingLotService parkingLotService = ParkingLotService.getInstance();

    @Before
    public void init() {
        parkingLotService.create(PARKING_LOT_CAPACITY);
    }

    @Test
    public void test_assign_first_parkingSlot_Successful() {
        //when
        parkingSlotService.assignSlot(REGISTRATION_NUMBER);
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
        parkingSlotService.assignSlot(REGISTRATION_NUMBER);
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

    @Test
    public void test_unassign_car() {
        //given
        assignSlots();
        //when
        parkingSlotService.unassignSlot(REGISTRATION_NUMBER, 5);
        //then
        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
        Assert.assertFalse(parkingSlots.get(1).isOccupied());
        Assert.assertTrue(parkingSlots
                .stream()
                .anyMatch(slot -> slot.getRegistrationNumber() == null));
    }

    private void assignSlots() {
        List<ParkingSlot> slots = ParkingLot.getInstance().getParkingSlots();
        assignSlot(slots.get(0), Mockito.anyString());
        assignSlot(slots.get(1), REGISTRATION_NUMBER);
        assignSlot(slots.get(2), Mockito.anyString());
    }

    private void assignSlot(ParkingSlot slot, String registrationNumber) {
        slot.setRegistrationNumber(registrationNumber);
        slot.setOccupied(true);
    }

    @Test
    public void test_unassign_car_not_found() {
        //given
        assignAllSlots();
        //when
        parkingSlotService.unassignSlot(REGISTRATION_NUMBER, 5);
        //then
        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
        Assert.assertFalse(parkingSlots
                .stream()
                .anyMatch(slot -> slot.getRegistrationNumber().equals(REGISTRATION_NUMBER)));
    }


}
