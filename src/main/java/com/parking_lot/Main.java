package com.parking_lot;

import com.parking_lot.model.ParkingLot;
import com.parking_lot.model.ParkingSlot;
import com.parking_lot.services.ParkingLotService;
import com.parking_lot.services.ParkingSlotService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\poojbhat\\Downloads\\parking_lot_2.0.0_(1)(3) (1)\\parking_lot_2.0.0\\functional_spec\\fixtures\\file_input.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] input = line.split(" ");
                performOperation(input);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void performOperation(String[] input) {
        switch (input[0]) {
            case "create_parking_lot":
                createSlots(input);
                break;
            case "park":
                parkVehicle(input);
                break;
            case "leave":
                leaveParkingSpot(input);
                break;
            case "status":
                printStatus();
                break;
            default:
                System.exit(0);
        }
    }

    private static void printStatus() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        System.out.println("Slot No. Registration No.");
        parkingLot.getParkingSlots()
                .stream()
                .filter(ParkingSlot::isOccupied)
                .forEach(slot -> System.out.println(slot.getSlotNumber() + " " + slot.getRegistrationNumber()));
    }

    private static void createSlots(String[] input) {
        int numberOfSpots = Integer.parseInt(input[1]);
        ParkingLotService.getInstance().create(numberOfSpots);
    }

    private static void parkVehicle(String[] input) {
        String registrationNumber = input[1];
        ParkingSlotService.getInstance().assignSlot(registrationNumber);
    }

    private static void leaveParkingSpot(String[] input) {
        String registrationNumber = input[1];
        int hours = Integer.parseInt(input[2]);
        ParkingSlotService.getInstance().unassignSlot(registrationNumber, hours);
    }

}
