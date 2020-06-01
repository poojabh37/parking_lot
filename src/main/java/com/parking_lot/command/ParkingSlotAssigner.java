package com.parking_lot.command;

import com.parking_lot.model.Car;
import com.parking_lot.services.ParkingSlotService;

public class ParkingSlotAssigner implements CommandExecutor {

    private ParkingSlotService parkingService = ParkingSlotService.getInstance();

    @Override
    public void execute(String[] arguments) {
        Car car = getCar(arguments);
        parkingService.assignSlot(car);
    }

    private Car getCar(String[] arguments) {
        String registrationNumber = arguments[1];
        String color = arguments[2];
        return new Car(registrationNumber, color);
    }

}
