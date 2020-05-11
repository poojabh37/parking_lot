package com.parking_lot.services;

import java.util.ArrayList;
import java.util.List;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

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
        parkingLot.setParkingSlots(createParkingSlots(capacity));
        System.out.println("Created parking lot with " + capacity + " slots");
    }

    private List<ParkingSlot> createParkingSlots(int capacity) {
        List<ParkingSlot> slots = new ArrayList<>();
        for (int slotNumber = 1; slotNumber <= capacity; slotNumber++) {
            slots.add(new ParkingSlot(slotNumber));
        }
        return slots;
    }

    public void printStatus() {
        List<ParkingSlot> slots = ParkingLot.getInstance().getParkingSlots();
        System.out.println("Slot No.    Registration No.");
        slots.stream()
                .filter(ParkingSlot::isOccupied)
                .forEach(s -> System.out.println(s.getSlotNumber() + "           "
                        + s.getRegistrationNumber()));
    }
}
