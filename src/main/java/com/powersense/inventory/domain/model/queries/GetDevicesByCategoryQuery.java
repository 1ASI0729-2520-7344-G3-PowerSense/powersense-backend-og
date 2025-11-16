package com.powersense.inventory.domain.model.queries;

import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;

/**
 * Get Devices By Category Query
 */
public record GetDevicesByCategoryQuery(DeviceCategory category) {
    public GetDevicesByCategoryQuery {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
    }
}