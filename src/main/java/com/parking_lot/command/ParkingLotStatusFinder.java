package com.parking_lot.command;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import java.util.Comparator;
import java.util.Map;

public class ParkingLotStatusFinder implements CommandExecutor {

    @Override
    public void execute(String[] arguments) {
        Map<String, ParkingSlot> occupiedSlots = ParkingLot.getInstance().getOccupiedParkingSlots();
        printStatus(occupiedSlots);
    }

    private void printStatus(Map<String, ParkingSlot> occupiedSlots) {
        System.out.println("Slot No.    Registration No.");
        occupiedSlots.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(ParkingSlot::getSlotNumber)))
                .forEach(entry -> System.out.println(entry.getValue().getSlotNumber() + "           "
                        + entry.getKey()));
    }

}
