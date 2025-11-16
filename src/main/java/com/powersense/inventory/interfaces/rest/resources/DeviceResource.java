package com.powersense.inventory.interfaces.rest.resources;

import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Device Resource
 */
public record DeviceResource(
        UUID id,
        String name,
        DeviceCategory category,
        DeviceStatuses status,
        LocationResource location,
        PowerInfoResource power,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record LocationResource(
            UUID roomId,
            String roomName
    ) {}

    public record PowerInfoResource(
            Double watts,
            Double voltage,
            Double amperage,
            Double kilowatts
    ) {}
}