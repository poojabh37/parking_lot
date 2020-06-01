package com.parking_lot.services;

import com.parking_lot.exception.ParkingLotFullException;
import com.parking_lot.exception.RegistrationNumberNotFoundException;
import com.parking_lot.model.Car;
import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import java.util.Optional;

public class ParkingSlotService {

    private static ParkingSlotService instance;

    private ParkingSlotService() {

    }

    public static ParkingSlotService getInstance() {
        if (instance == null) {
            instance = new ParkingSlotService();
        }
        return instance;
    }

    public void assignSlot(Car car) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Optional<ParkingSlot> available = parkingLot.getParkingSlots()
                .stream()
                .filter(slot -> !slot.isOccupied())
                .findFirst();
        if (!available.isPresent()) {
            throw new ParkingLotFullException();
        } else {
            assign(available.get(), car);
        }
    }

    private void assign(ParkingSlot slot, Car car) {
        slot.setOccupied(true);
        slot.setCar(car);
        System.out.println("Allocated slot number: " + slot.getSlotNumber());
    }

    public void unassignSlot(String registrationNumber, int hours) {
        Optional<ParkingSlot> slot = getParkingSlot(registrationNumber);
        if (!slot.isPresent()) {
            throw new RegistrationNumberNotFoundException();
        }
        unAssign(slot.get(), registrationNumber, hours);
    }

    private Optional<ParkingSlot> getParkingSlot(String registrationNumber) {
        return ParkingLot.getInstance().getParkingSlots()
                .stream()
                .filter(slot -> isMatchingSlot(slot, registrationNumber))
                .findFirst();
    }

    private boolean isMatchingSlot(ParkingSlot slot, String registrationNumber) {
        return slot.isOccupied() && slot.getCar().getRegistrationNumber().equals(registrationNumber);
    }

    private void unAssign(ParkingSlot slot, String registrationNumber, int hours) {
        slot.setOccupied(false);
        slot.setCar(null);

        int charge = ParkingChargeCalculator.calculateCharge(hours);
        System.out.println("Registration number " + registrationNumber +
                " with Slot Number " + slot.getSlotNumber() + " is free with Charge " + charge);
    }

}
