package com.parking_lot.services;

import com.parking_lot.exception.ParkingLotFullException;
import com.parking_lot.exception.RegistrationNumberNotFoundException;
import com.parking_lot.model.Car;
import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import java.util.Map;

public class ParkingSlotService {

    private static ParkingSlotService instance;
    private ParkingLot parkingLot = ParkingLot.getInstance();

    private ParkingSlotService() {

    }

    public static ParkingSlotService getInstance() {
        if (instance == null) {
            instance = new ParkingSlotService();
        }
        return instance;
    }

    public ParkingSlot assignSlot(Car car) {
        validateParkingLotFull();
        ParkingSlot available = parkingLot.getFreeParkingSlots().first();
        available.setCar(car);
        parkingLot.getFreeParkingSlots().remove(available);
        parkingLot.getOccupiedParkingSlots().put(car.getRegistrationNumber(), available);
        return available;
    }

    private void validateParkingLotFull() {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException();
        }
    }

    private boolean isParkingLotFull() {
        return ParkingLot.getInstance().getFreeParkingSlots().isEmpty();
    }

    public void unassignSlot(String registrationNumber, int hours) {
        Map<String, ParkingSlot> occupiedSlots = parkingLot.getOccupiedParkingSlots();
        validateRegistrationNumberPresent(occupiedSlots, registrationNumber);
        ParkingSlot slot = occupiedSlots.get(registrationNumber);
        unAssign(slot, registrationNumber, hours);
    }

    private void validateRegistrationNumberPresent(Map<String, ParkingSlot> occupiedSlots,
                                                   String registrationNumber) {
        if (!occupiedSlots.containsKey(registrationNumber)) {
            throw new RegistrationNumberNotFoundException();
        }

    }

    private void unAssign(ParkingSlot slot, String registrationNumber, int hours) {
        slot.setCar(null);

        int charge = ParkingChargeCalculator.calculateCharge(hours);
        System.out.println("Registration number " + registrationNumber +
                " with Slot Number " + slot.getSlotNumber() + " is free with Charge " + charge);
    }

}
