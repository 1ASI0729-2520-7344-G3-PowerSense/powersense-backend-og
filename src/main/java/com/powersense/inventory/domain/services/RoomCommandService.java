package com.powersense.inventory.domain.services;

import com.powersense.inventory.domain.model.aggregates.Room;
import com.powersense.inventory.domain.model.commands.CreateRoomCommand;
import com.powersense.inventory.domain.model.commands.UpdateRoomCommand;

import java.util.UUID;

/**
 * Room Command Service
 * Port (interface) for room write operations
 */
public interface RoomCommandService {

    /**
     * Handle CreateRoomCommand
     *
     * @param command CreateRoomCommand
     * @return Created Room
     */
    Room handle(CreateRoomCommand command);

    /**
     * Handle UpdateRoomCommand
     *
     * @param command UpdateRoomCommand
     * @return Updated Room
     */
    Room handle(UpdateRoomCommand command);

    /**
     * Delete a room by ID
     *
     * @param roomId Room identifier
     */
    void deleteById(UUID roomId);
}