package com.powersense.inventory.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Room Resource
 * DTO for room responses
 *
 * @param id Room identifier
 * @param name Room name
 * @param createdAt Creation timestamp
 * @param updatedAt Last update timestamp
 */
public record RoomResource(
        UUID id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}