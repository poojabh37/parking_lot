package com.parking_lot.services;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import java.util.TreeSet;

public class ParkingLotService {

    private static ParkingLotService instance;

    private ParkingLotService() {

    }

    public static ParkingLotService getInstance() {
        if (instance == null) {
            instance = new ParkingLotService();
        }
        return instance;
    }

    public void create(int capacity) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.setTotalCapacity(capacity);
        parkingLot.setFreeParkingSlots(createParkingSlots(capacity));
        System.out.println("Created parking lot with " + capacity + " slots");
    }

    private TreeSet<ParkingSlot> createParkingSlots(int capacity) {
        TreeSet<ParkingSlot> slots = new TreeSet<>();
        for (int slotNumber = 1; slotNumber <= capacity; slotNumber++) {
            slots.add(new ParkingSlot(slotNumber));
        }
        return slots;
    }

}
