package com.parking_lot.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class ParkingLot {

    private static ParkingLot instance;
    private Map<String, ParkingSlot> occupiedParkingSlots;
    private TreeSet<ParkingSlot> freeParkingSlots;
    private int totalCapacity;

    private ParkingLot() {

    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public Map<String, ParkingSlot> getOccupiedParkingSlots() {
        return occupiedParkingSlots;
    }

    public void setOccupiedParkingSlots(Map<String, ParkingSlot> occupiedParkingSlots) {
        this.occupiedParkingSlots = occupiedParkingSlots;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public TreeSet<ParkingSlot> getFreeParkingSlots() {
        return freeParkingSlots;
    }

    public void setFreeParkingSlots(TreeSet<ParkingSlot> freeParkingSlots) {
        this.freeParkingSlots = freeParkingSlots;
    }
}
