package com.parking_lot.services;

import com.parking_lot.exception.ParkingLotFullException;
import com.parking_lot.model.Car;
import com.parking_lot.model.ParkingLot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.parking_lot.services.Constants.PARKING_LOT_CAPACITY;
import static com.parking_lot.services.Constants.REGISTRATION_NUMBER;

public class ParkingSlotServiceTest {

    private ParkingSlotService parkingSlotService = ParkingSlotService.getInstance();
    private ParkingLotService parkingLotService = ParkingLotService.getInstance();
    private ParkingLot parkingLot = ParkingLot.getInstance();

    @Before
    public void init() {
        parkingLotService.create(PARKING_LOT_CAPACITY);
    }

    @Test
    public void test_assign_first_parkingSlot_Successful() {
        //when
        parkingSlotService.assignSlot(new Car(REGISTRATION_NUMBER, "blue"));
        //then
        Assert.assertNotNull(parkingLot.getRegistrationNumberToOccupiedParkingSlots().get(REGISTRATION_NUMBER));
        Assert.assertEquals(1, parkingLot.getRegistrationNumberToOccupiedParkingSlots().size());
        Assert.assertEquals(PARKING_LOT_CAPACITY - 1, parkingLot.getFreeParkingSlots().size());
    }

    @Test(expected = ParkingLotFullException.class)
    public void test_assign_parkingSlot_no_Space_available_throws_Exception() {
        //given
        assignAllSlots();
        //when
        parkingSlotService.assignSlot(new Car(REGISTRATION_NUMBER, "red"));
    }

    @Test
    public void test_unassign_car() {
        //given
        assignAllSlots();
        //when
        parkingSlotService.unassignSlot(REGISTRATION_NUMBER, 5);
        //then
        Assert.assertNull(parkingLot.getRegistrationNumberToOccupiedParkingSlots().get(REGISTRATION_NUMBER));
        Assert.assertEquals(PARKING_LOT_CAPACITY-1, parkingLot.getRegistrationNumberToOccupiedParkingSlots().size());
        Assert.assertEquals(1, parkingLot.getFreeParkingSlots().size());
    }

    private void assignAllSlots() {
        parkingSlotService.assignSlot(new Car("CH 12 PB 0987", "blue"));
        parkingSlotService.assignSlot(new Car(REGISTRATION_NUMBER, "red"));
        parkingSlotService.assignSlot(new Car("CH 06 GH 2387", "silver"));
    }

}
