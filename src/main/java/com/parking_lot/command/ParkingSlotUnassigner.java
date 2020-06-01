package com.parking_lot.command;

import com.parking_lot.services.ParkingSlotService;

public class ParkingSlotUnassigner implements CommandExecutor {

    private ParkingSlotService parkingService = ParkingSlotService.getInstance();

    @Override
    public void execute(String[] arguments) {
        String registrationNumber = arguments[1];
        int hours = Integer.parseInt(arguments[2]);
        parkingService.unassignSlot(registrationNumber, hours);
    }

}
