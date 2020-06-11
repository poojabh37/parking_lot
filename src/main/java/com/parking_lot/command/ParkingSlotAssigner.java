package com.parking_lot.command;

import com.parking_lot.model.Car;
import com.parking_lot.model.ParkingSlot;
import com.parking_lot.services.ParkingSlotService;

class ParkingSlotAssigner implements CommandExecutor {

    private ParkingSlotService parkingService = ParkingSlotService.getInstance();

    @Override
    public void execute(String[] arguments) {
        Car car = getCar(arguments);
        ParkingSlot slot = parkingService.assignSlot(car);
        System.out.println("Allocated slot number: " + slot.getSlotNumber());
    }

    private Car getCar(String[] arguments) {
        String registrationNumber = arguments[1];
        String color = arguments[2];
        return new Car(registrationNumber, color);
    }

}
