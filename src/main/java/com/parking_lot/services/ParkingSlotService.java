package com.parking_lot.services;

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

    public void assignParkingSpot(String registrationNumber) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Optional<ParkingSlot> available = parkingLot.getParkingSlots()
                .stream()
                .filter(slot -> !slot.isOccupied())
                .findFirst();
        if (available.isPresent()) {
            assign(available.get(), registrationNumber);
        } else {
            System.out.println("Sorry, parking lot is full");
        }
    }

    private void assign(ParkingSlot slot, String registrationNumber) {
        slot.setOccupied(true);
        slot.setRegistrationNumber(registrationNumber);
        System.out.println("Allocated slot number: " + slot.getSlotNumber());
    }

    public void unAssignParkingSpot(String registrationNumber, int hours) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        Optional<ParkingSlot> available = parkingLot.getParkingSlots()
                .stream()
                .filter(slot -> slot.getRegistrationNumber().equals(registrationNumber))
                .findFirst();
        if (available.isPresent()) {
            unAssign(available.get(), registrationNumber, hours);
        } else {
            System.out.println("Registration number " + registrationNumber + " not found");
        }
    }

    private void unAssign(ParkingSlot slot, String registrationNumber, int hours) {
        slot.setOccupied(false);
        slot.setRegistrationNumber(null);

        int charge = ParkingChargeCalculator.calculateCharge(hours);
        System.out.println("Registration number " + registrationNumber +
                " with Slot Number " + slot.getSlotNumber() + " is free with Charge " + charge);
    }


}
