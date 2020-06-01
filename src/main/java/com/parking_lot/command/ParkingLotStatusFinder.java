package com.parking_lot.command;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import java.util.List;

public class ParkingLotStatusFinder implements CommandExecutor {

    @Override
    public void execute(String[] arguments) {
        List<ParkingSlot> slots = ParkingLot.getInstance().getParkingSlots();
        System.out.println("Slot No.    Registration No.");
        slots.stream()
                .filter(ParkingSlot::isOccupied)
                .forEach(s -> System.out.println(s.getSlotNumber() + "           "
                        + s.getCar().getRegistrationNumber()));
    }

}
