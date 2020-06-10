package com.parking_lot;

import com.parking_lot.command.RequestHandler;
import com.parking_lot.exception.ParkingLotFullException;
import com.parking_lot.exception.RegistrationNumberNotFoundException;
import com.parking_lot.exception.TooFewArgumentsException;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        String fileName = getFileName(args);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String input;
            while ((input = reader.readLine()) != null) {
                RequestHandler.handleRequest(input);
            }
        } catch (TooFewArgumentsException e) {
            System.out.println(e.getMessage());
        } catch (RegistrationNumberNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParkingLotFullException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getFileName(String[] args) {
        if (args.length == 0) {
            throw new TooFewArgumentsException();
        }
        return args[0];
    }

}
