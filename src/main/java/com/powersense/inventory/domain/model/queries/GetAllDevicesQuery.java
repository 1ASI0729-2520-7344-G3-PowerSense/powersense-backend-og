package com.powersense.inventory.domain.model.queries;

import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;

import java.util.UUID;

/**
 * Get All Devices Query
 * Query with optional filters
 *
 * @param search Search term (searches in name and room name)
 * @param category Filter by category
 * @param roomId Filter by room
 * @param status Filter by status
 */
public record GetAllDevicesQuery(
        String search,
        DeviceCategory category,
        UUID roomId,
        DeviceStatuses status
) {
}