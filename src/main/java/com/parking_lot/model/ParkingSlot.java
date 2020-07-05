package com.parking_lot.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot slot = (ParkingSlot) o;
        return slotNumber == slot.slotNumber &&
                Objects.equals(car, slot.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber, car);
    }
}
