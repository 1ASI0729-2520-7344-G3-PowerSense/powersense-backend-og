package com.powersense.inventory.interfaces.rest.resources;

import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/**
 * Create Device Resource
 */
public record CreateDeviceResource(
        @NotBlank(message = "Device name is required")
        @Size(max = 100, message = "Device name cannot exceed 100 characters")
        String name,

        @NotNull(message = "Device category is required")
        DeviceCategory category,

        @NotNull(message = "Device status is required")
        DeviceStatuses status,

        @NotNull(message = "Room ID is required")
        UUID roomId,

        @NotBlank(message = "Room name is required")
        String roomName,

        @NotNull(message = "Watts is required")
        @Positive(message = "Watts must be positive")
        Double watts,

        Double voltage,

        Double amperage
) {
}