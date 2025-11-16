package com.powersense.inventory.interfaces.rest.resources;

import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;
import jakarta.validation.constraints.NotNull;

/**
 * Update Device Status Resource
 */
public record UpdateDeviceStatusResource(
        @NotNull(message = "Status is required")
        DeviceStatuses status
) {
}