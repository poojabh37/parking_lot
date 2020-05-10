package com.parking_lot.services;

import com.parking_lot.model.ParkingLot;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotServiceTest {

    private ParkingLotService parkingLotCreatorService = ParkingLotService.getInstance();

    @Test
    public void test_parkingLot_created_successfully() {
        parkingLotCreatorService.create(5);
        Assert.assertEquals(5, ParkingLot.getInstance().getTotalCapacity());
    }
}
