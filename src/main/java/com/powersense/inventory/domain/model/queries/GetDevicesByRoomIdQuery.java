package com.powersense.inventory.domain.model.queries;

import java.util.UUID;

/**
 * Get Devices By Room ID Query
 */
public record GetDevicesByRoomIdQuery(UUID roomId) {
    public GetDevicesByRoomIdQuery {
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
    }
}