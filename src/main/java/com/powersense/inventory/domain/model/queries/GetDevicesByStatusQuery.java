package com.powersense.inventory.domain.model.queries;

import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;

/**
 * Get Devices By Status Query
 */
public record GetDevicesByStatusQuery(DeviceStatuses status) {
    public GetDevicesByStatusQuery {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }
}