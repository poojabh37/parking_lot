package com.parking_lot.command;

public class RequestHandler {

    public static void handleRequest(String input) {
        String[] operationArguments = input.split(" ");
        Command command = Command.valueOf(operationArguments[0].toUpperCase());// what if this command is not found
        CommandExecutor commandExecutor = CommandExecutorCollector.getCommandExecutor(command);
        commandExecutor.execute(operationArguments);
    }
}
