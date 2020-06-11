package com.parking_lot.command;

import com.parking_lot.services.ParkingLotService;

class ParkingLotCreator implements CommandExecutor {

    private ParkingLotService parkingService = ParkingLotService.getInstance();

    @Override
    public void execute(String[] arguments) {
        int totalSlots = Integer.parseInt(arguments[1]);
        parkingService.create(totalSlots);
    }
}
