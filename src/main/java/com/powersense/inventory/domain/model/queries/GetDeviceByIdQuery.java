package com.powersense.inventory.domain.model.queries;

import java.util.UUID;

/**
 * Get Device By ID Query
 */
public record GetDeviceByIdQuery(UUID deviceId) {
    public GetDeviceByIdQuery {
        if (deviceId == null) {
            throw new IllegalArgumentException("Device ID cannot be null");
        }
    }
}