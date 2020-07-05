package com.parking_lot.command;

import java.util.HashMap;
import java.util.Map;

import static com.parking_lot.command.Command.*;

class CommandExecutorCollector {

    private static Map<Command, CommandExecutor> commandVsCommandExecutor = new HashMap<>();

    static {
        commandVsCommandExecutor.put(CREATE_PARKING_LOT, new ParkingLotCreator()); // should it be Command.CREATE_PARKING_LOT ?? i mean what is the type of CREATE_PARKING_LOT
        commandVsCommandExecutor.put(PARK, new ParkingSlotAssigner());
        commandVsCommandExecutor.put(LEAVE, new ParkingSlotUnassigner());
        commandVsCommandExecutor.put(STATUS, new ParkingLotStatusFinder());
    }

    static CommandExecutor getCommandExecutor(Command command) {
        return commandVsCommandExecutor.get(command);
    }

}
