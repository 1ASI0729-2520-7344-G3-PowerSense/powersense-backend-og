package com.powersense.inventory.domain.model.commands;

import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;

import java.util.UUID;

/**
 * Update Device Command
 */
public record UpdateDeviceCommand(
        UUID deviceId,
        String name,
        DeviceCategory category,
        DeviceStatuses status,
        UUID roomId,
        String roomName,
        Double watts,
        Double voltage,
        Double amperage
) {
    public UpdateDeviceCommand {
        if (deviceId == null) {
            throw new IllegalArgumentException("Device ID cannot be null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Device name cannot be null or empty");
        }
        if (category == null) {
            throw new IllegalArgumentException("Device category cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Device status cannot be null");
        }
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        if (roomName == null || roomName.isBlank()) {
            throw new IllegalArgumentException("Room name cannot be null or empty");
        }
        if (watts == null || watts < 0) {
            throw new IllegalArgumentException("Watts must be a positive number");
        }
    }
}