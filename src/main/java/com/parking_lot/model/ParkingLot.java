package com.parking_lot.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<ParkingSlot> parkingSlots = new ArrayList<>();
    private int totalCapacity;

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
}
