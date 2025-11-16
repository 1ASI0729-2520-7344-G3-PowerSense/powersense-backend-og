package com.powersense.inventory.domain.model.commands;

import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;

import java.util.UUID;

/**
 * Create Device Command
 *
 * @param name Device name
 * @param category Device category
 * @param status Device status
 * @param roomId Room identifier
 * @param roomName Room name
 * @param watts Power consumption in watts
 * @param voltage Voltage (optional)
 * @param amperage Amperage (optional)
 */
public record CreateDeviceCommand(
        String name,
        DeviceCategory category,
        DeviceStatuses status,
        UUID roomId,
        String roomName,
        Double watts,
        Double voltage,
        Double amperage
) {
    public CreateDeviceCommand {
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