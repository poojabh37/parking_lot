package com.parking_lot.command;

import com.parking_lot.exception.OperationNotFoundException;

public class RequestHandler {

    public static void handleRequest(String input) {
        String[] operationArguments = input.split(" ");
        Command command = getCommand(operationArguments[0]);// what if this command is not found
        CommandExecutor commandExecutor = CommandExecutorCollector.getCommandExecutor(command);
        commandExecutor.execute(operationArguments);
    }

    private static Command getCommand(String operation) {
        Command command;
        try {
            command = Command.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new OperationNotFoundException();
        }
        return command;

    }
}
