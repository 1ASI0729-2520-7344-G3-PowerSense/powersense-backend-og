package com.powersense.inventory.interfaces.rest.resources;

import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * Bulk Update Device Status Resource
 */
public record BulkUpdateDeviceStatusResource(
        @NotEmpty(message = "Device IDs list cannot be empty")
        List<UUID> deviceIds,

        @NotNull(message = "Status is required")
        DeviceStatuses status
) {
}