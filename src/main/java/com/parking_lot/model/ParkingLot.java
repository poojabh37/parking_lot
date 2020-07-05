package com.parking_lot.model;

import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class ParkingLot {

    private static ParkingLot instance;
    private Map<String, ParkingSlot> registrationNumberToOccupiedParkingSlots;
    private TreeSet<ParkingSlot> freeParkingSlots;

    private ParkingLot() {

    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public Map<String, ParkingSlot> getRegistrationNumberToOccupiedParkingSlots() {
        return registrationNumberToOccupiedParkingSlots;
    }

    public void setRegistrationNumberToOccupiedParkingSlots(Map<String, ParkingSlot> registrationNumberToOccupiedParkingSlots) {
        this.registrationNumberToOccupiedParkingSlots = registrationNumberToOccupiedParkingSlots;
    }

    public TreeSet<ParkingSlot> getFreeParkingSlots() {
        return freeParkingSlots;
    }

    public void setFreeParkingSlots(TreeSet<ParkingSlot> freeParkingSlots) {
        this.freeParkingSlots = freeParkingSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return Objects.equals(registrationNumberToOccupiedParkingSlots, that.registrationNumberToOccupiedParkingSlots) &&
                Objects.equals(freeParkingSlots, that.freeParkingSlots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumberToOccupiedParkingSlots, freeParkingSlots);
    }
}
