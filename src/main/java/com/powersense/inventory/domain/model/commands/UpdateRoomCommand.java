package com.powersense.inventory.domain.model.commands;

import java.util.UUID;

/**
 * Update Room Command
 * Command to update an existing room
 *
 * @param roomId Room identifier
 * @param name New room name
 */
public record UpdateRoomCommand(UUID roomId, String name) {

    /**
     * Validates the command
     *
     * @throws IllegalArgumentException if validation fails
     */
    public UpdateRoomCommand {
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Room name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Room name cannot exceed 100 characters");
        }
    }
}