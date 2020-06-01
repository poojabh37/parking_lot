package com.parking_lot;

import com.parking_lot.command.Command;
import com.parking_lot.command.CommandExecutor;
import com.parking_lot.command.CommandExecutorCollector;
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
                performOperation(input);
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

    private static void performOperation(String input) {
        String[] operationArguments = input.split(" ");
        Command command = Command.valueOf(operationArguments[0].toUpperCase());
        CommandExecutor commandExecutor = getCommandExecutor(command);
        commandExecutor.execute(operationArguments);
    }

    private static CommandExecutor getCommandExecutor(Command command) {
        CommandExecutorCollector executorCollector = new CommandExecutorCollector();
        return executorCollector.getCommandVsCommandExecutor().get(command);
    }

}
