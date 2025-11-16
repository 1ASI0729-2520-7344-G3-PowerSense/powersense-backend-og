package com.powersense.inventory.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Update Room Resource
 * DTO for updating a room
 *
 * @param name New room name
 */
public record UpdateRoomResource(
        @NotBlank(message = "Room name is required")
        @Size(max = 100, message = "Room name cannot exceed 100 characters")
        String name
) {
}