package com.powersense.inventory.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Create Room Resource
 * DTO for creating a new room
 *
 * @param name Room name
 */
public record CreateRoomResource(
        @NotBlank(message = "Room name is required")
        @Size(max = 100, message = "Room name cannot exceed 100 characters")
        String name
) {
}