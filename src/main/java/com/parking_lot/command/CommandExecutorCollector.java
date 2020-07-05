package com.parking_lot.command;

import java.util.HashMap;
import java.util.Map;

import static com.parking_lot.command.Command.*;

class CommandExecutorCollector {

    private static Map<Command, CommandExecutor> commandToCommandExecutor = new HashMap<>();

    static {
        commandToCommandExecutor.put(CREATE_PARKING_LOT, new ParkingLotCreator());
        commandToCommandExecutor.put(PARK, new ParkingSlotAssigner());
        commandToCommandExecutor.put(LEAVE, new ParkingSlotUnassigner());
        commandToCommandExecutor.put(STATUS, new ParkingLotStatusFinder());
    }

    static CommandExecutor getCommandExecutor(Command command) {
        return commandToCommandExecutor.get(command);
    }

}
