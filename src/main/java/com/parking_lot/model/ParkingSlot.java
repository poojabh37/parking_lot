package com.parking_lot.model;

public class ParkingSlot implements Comparable {

    private int slotNumber;
    private Car car;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public int compareTo(Object slot) {
        return this.slotNumber - ((ParkingSlot) slot).getSlotNumber();
    }
}
