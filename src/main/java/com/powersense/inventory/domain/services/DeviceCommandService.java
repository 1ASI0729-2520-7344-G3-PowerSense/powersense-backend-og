package com.powersense.inventory.domain.services;

import com.powersense.inventory.domain.model.aggregates.Device;
import com.powersense.inventory.domain.model.commands.BulkUpdateDeviceStatusCommand;
import com.powersense.inventory.domain.model.commands.CreateDeviceCommand;
import com.powersense.inventory.domain.model.commands.UpdateDeviceCommand;
import com.powersense.inventory.domain.model.commands.UpdateDeviceStatusCommand;

import java.util.List;
import java.util.UUID;

/**
 * Device Command Service
 * Port (interface) for device write operations
 */
public interface DeviceCommandService {

    /**
     * Handle CreateDeviceCommand
     */
    Device handle(CreateDeviceCommand command);

    /**
     * Handle UpdateDeviceCommand
     */
    Device handle(UpdateDeviceCommand command);

    /**
     * Handle UpdateDeviceStatusCommand
     */
    Device handle(UpdateDeviceStatusCommand command);

    /**
     * Handle BulkUpdateDeviceStatusCommand
     */
    List<Device> handle(BulkUpdateDeviceStatusCommand command);

    /**
     * Update all devices in a room to a specific status
     */
    List<Device> updateRoomDevicesStatus(UUID roomId, com.powersense.inventory.domain.model.valueobjects.DeviceStatuses status);

    /**
     * Delete a device by ID
     */
    void deleteById(UUID deviceId);
}