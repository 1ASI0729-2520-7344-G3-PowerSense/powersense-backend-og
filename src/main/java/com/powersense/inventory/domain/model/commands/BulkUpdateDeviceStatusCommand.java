package com.powersense.inventory.domain.model.commands;

import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;

import java.util.List;
import java.util.UUID;

/**
 * Bulk Update Device Status Command
 * Updates status for multiple devices
 */
public record BulkUpdateDeviceStatusCommand(
        List<UUID> deviceIds,
        DeviceStatuses status
) {
    public BulkUpdateDeviceStatusCommand {
        if (deviceIds == null || deviceIds.isEmpty()) {
            throw new IllegalArgumentException("Device IDs list cannot be null or empty");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }
}