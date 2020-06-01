package com.parking_lot.services;

import com.parking_lot.exception.ParkingLotFullException;
import com.parking_lot.exception.RegistrationNumberNotFoundException;
import com.parking_lot.model.Car;
import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

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
        parkingSlotService.assignSlot(new Car(REGISTRATION_NUMBER, "blue"));
        //then
        ParkingSlot parkingSlot = ParkingLot.getInstance().getParkingSlots().get(0);
        Assert.assertTrue(parkingSlot.isOccupied());
        Assert.assertEquals(REGISTRATION_NUMBER, parkingSlot.getCar().getRegistrationNumber());
    }

    @Test(expected = ParkingLotFullException.class)
    public void test_assign_parkingSlot_no_Space_available_throws_Exception() {
        //given
        assignAllSlots();
        //when
        parkingSlotService.assignSlot(new Car(REGISTRATION_NUMBER, "red"));
        //then
        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
        Assert.assertFalse(parkingSlots
                .stream()
                .anyMatch(slot -> slot.getCar().getRegistrationNumber().equals(REGISTRATION_NUMBER)));
    }

    private void assignAllSlots() {
        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
        for (ParkingSlot slot : parkingSlots) {
            slot.setOccupied(true);
            slot.setCar(new Car(Mockito.anyString(), Mockito.anyString()));
        }
    }

    @Test
    public void test_unassign_car() {
//        //given
//        assignSlots();
//        //when
//        parkingSlotService.unassignSlot(REGISTRATION_NUMBER, 5);
//        //then
//        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
//        Assert.assertFalse(parkingSlots.get(1).isOccupied());
//        Assert.assertTrue(parkingSlots
//                .stream()
//                .anyMatch(slot -> slot.getCar().getRegistrationNumber() == null));
    }

    private void assignSlots() {
        List<ParkingSlot> slots = ParkingLot.getInstance().getParkingSlots();
        assignSlot(slots.get(0), Mockito.anyString());
        assignSlot(slots.get(1), REGISTRATION_NUMBER);
        assignSlot(slots.get(2), Mockito.anyString());
    }

    private void assignSlot(ParkingSlot slot, String registrationNumber) {
        slot.setCar(new Car(registrationNumber, "red"));
        slot.setOccupied(true);
    }

    @Test(expected = RegistrationNumberNotFoundException.class)
    public void test_unassign_car_not_found() {
        //given
        assignAllSlots();
        //when
        parkingSlotService.unassignSlot(REGISTRATION_NUMBER, 5);
        //then
        List<ParkingSlot> parkingSlots = ParkingLot.getInstance().getParkingSlots();
        Assert.assertFalse(parkingSlots
                .stream()
                .anyMatch(slot -> slot.getCar().getRegistrationNumber().equals(REGISTRATION_NUMBER)));
    }


}
