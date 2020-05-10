package com.parking_lot.services;

import com.parking_lot.model.ParkingLot;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotCreatorServiceTest {

    private ParkingLotCreatorService parkingLotCreatorService = ParkingLotCreatorService.getInstance();

    @Test
    public void test_parkingLot_created_successfully() {
        parkingLotCreatorService.createParkingLot(5);
        Assert.assertEquals(5, ParkingLot.getInstance().getTotalCapacity());
    }
}
