package com.powersense.inventory.domain.model.commands;

import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;

import java.util.UUID;

/**
 * Update Device Status Command
 */
public record UpdateDeviceStatusCommand(
        UUID deviceId,
        DeviceStatuses status
) {
    public UpdateDeviceStatusCommand {
        if (deviceId == null) {
            throw new IllegalArgumentException("Device ID cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }
}