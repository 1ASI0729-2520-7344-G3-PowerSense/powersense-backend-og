package com.powersense.inventory.domain.model.queries;

import java.util.UUID;

/**
 * Get Room By ID Query
 * Query to retrieve a room by its identifier
 *
 * @param roomId Room identifier
 */
public record GetRoomByIdQuery(UUID roomId) {

    /**
     * Validates the query
     *
     * @throws IllegalArgumentException if validation fails
     */
    public GetRoomByIdQuery {
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
    }
}