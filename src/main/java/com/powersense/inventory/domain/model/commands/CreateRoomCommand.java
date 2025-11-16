package com.powersense.inventory.domain.model.commands;

/**
 * Create Room Command
 * Command to create a new room
 * @param name Room name
 */
public record CreateRoomCommand(String name) {

    /**
     * Validates the command
     *
     * @throws IllegalArgumentException if validation fails
     */
    public CreateRoomCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Room name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Room name cannot exceed 100 characters");
        }
    }
}