package com.parking_lot.services;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.stream.IntStream;

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
        parkingLot.setOccupiedParkingSlots(new HashMap<>());
        System.out.println("Created parking lot with " + capacity + " slots");
    }

    private TreeSet<ParkingSlot> createParkingSlots(int capacity) {
        TreeSet<ParkingSlot> slots = new TreeSet<>();
        IntStream.rangeClosed(1, capacity)
                .forEachOrdered(slotNumber -> slots.add(new ParkingSlot(slotNumber)));
        return slots;
    }

}
