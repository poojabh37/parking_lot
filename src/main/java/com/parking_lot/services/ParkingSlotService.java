package com.parking_lot.services;

import com.parking_lot.exception.ParkingLotFullException;
import com.parking_lot.exception.RegistrationNumberNotFoundException;
import com.parking_lot.model.Car;
import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;

import java.util.Comparator;
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
        parkingLot.getRegistrationNumberToOccupiedParkingSlots()
                .put(car.getRegistrationNumber(), available);
        return available;
    }

    private void validateParkingLotFull() {
        if (isParkingLotFull()) {
            throw new ParkingLotFullException();
        }
    }

    private boolean isParkingLotFull() {
        return parkingLot.getFreeParkingSlots().isEmpty();
    }

    public void unassignSlot(String registrationNumber, int hours) {
        Map<String, ParkingSlot> occupiedSlots = parkingLot.getRegistrationNumberToOccupiedParkingSlots();
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
        parkingLot.getRegistrationNumberToOccupiedParkingSlots().remove(registrationNumber);
        slot.setCar(null);
        parkingLot.getFreeParkingSlots().add(slot);
        calculateCharge(slot.getSlotNumber(), registrationNumber, hours);
    }

    private void calculateCharge(int slotNumber, String registrationNumber, int hours) {
        int charge = ParkingChargeCalculator.calculateCharge(hours);
        System.out.println(String.format("Registration number %s with Slot Number %s is free with Charge %s",
                registrationNumber, slotNumber, charge));
    }

    public void printStatus(Map<String, ParkingSlot> occupiedSlots) {
        System.out.println("Slot No.    Registration No.");
        occupiedSlots.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(ParkingSlot::getSlotNumber)))
                .forEach(entry -> System.out.println(entry.getValue().getSlotNumber()
                        + "           " + entry.getKey()));
    }

}
