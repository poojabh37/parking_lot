package com.parking_lot.command;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;
import com.parking_lot.services.ParkingSlotService;

import java.util.Map;

class ParkingLotStatusFinder implements CommandExecutor {

    private ParkingSlotService parkingSlotService = ParkingSlotService.getInstance();

    @Override
    public void execute(String[] arguments) {
        Map<String, ParkingSlot> occupiedSlots = ParkingLot.getInstance().getRegistrationNumberToOccupiedParkingSlots();
        parkingSlotService.printStatus(occupiedSlots);
    }

}
