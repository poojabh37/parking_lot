package com.parking_lot.services;

import java.util.Optional;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

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

    public void assignSlot(String registrationNumber) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Optional<ParkingSlot> available = parkingLot.getParkingSlots()
                .stream()
                .filter(slot -> !slot.isOccupied())
                .findFirst();
        if (available.isPresent()) {
            assign(available.get(), registrationNumber);
        } else {
            assign(available.get(), car);
        }
    }

    private void assign(ParkingSlot slot, String registrationNumber) {
        slot.setOccupied(true);
        slot.setRegistrationNumber(registrationNumber);
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
        return slot.isOccupied() && slot.getRegistrationNumber().equals(registrationNumber);
    }

    private void unAssign(ParkingSlot slot, String registrationNumber, int hours) {
        slot.setOccupied(false);
        slot.setRegistrationNumber(null);

        int charge = ParkingChargeCalculator.calculateCharge(hours);
        System.out.println("Registration number " + registrationNumber +
                " with Slot Number " + slot.getSlotNumber() + " is free with Charge " + charge);
    }


}
