package com.parking_lot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.parking_lot.exception.TooFewArgumentsException;
import com.parking_lot.services.ParkingLotService;
import com.parking_lot.services.ParkingSlotService;

public class Main {

    public static void main(String[] args) {
        String fileName = getFileName(args);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] input = line.split(" ");
                performOperation(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileName(String[] args) {
        if (args.length == 0) {
            throw new TooFewArgumentsException();
        }
        return args[0];
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
                ParkingLotService.getInstance().printStatus();
                break;
            default:
                System.exit(0);
        }
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
