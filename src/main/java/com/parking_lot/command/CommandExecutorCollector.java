package com.parking_lot.command;

import java.util.HashMap;
import java.util.Map;

import static com.parking_lot.command.Command.*;

public class CommandExecutorCollector {

    private Map<Command, CommandExecutor> commandVsCommandExecutor = new HashMap<>();

    public CommandExecutorCollector() {
        commandVsCommandExecutor.put(CREATE_PARKING_LOT, new ParkingLotCreator());
        commandVsCommandExecutor.put(PARK, new ParkingSlotAssigner());
        commandVsCommandExecutor.put(LEAVE, new ParkingSlotUnassigner());
        commandVsCommandExecutor.put(STATUS, new ParkingLotStatusFinder());
    }

    public Map<Command, CommandExecutor> getCommandVsCommandExecutor() {
        return commandVsCommandExecutor;
    }

}
