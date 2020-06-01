package com.parking_lot.command;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import java.util.Map;

public class ParkingLotStatusFinder implements CommandExecutor {

    @Override
    public void execute(String[] arguments) {
        Map<String, ParkingSlot> occupiedSlots = ParkingLot.getInstance().getOccupiedParkingSlots();
        System.out.println("Slot No.    Registration No.");
        occupiedSlots
                .forEach((registrationNumber, slot) -> System.out.println(slot.getSlotNumber() + "           "
                        + registrationNumber));
    }

}
