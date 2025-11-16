package com.powersense.inventory.interfaces.rest.transform;

import com.powersense.inventory.domain.model.commands.UpdateDeviceCommand;
import com.powersense.inventory.interfaces.rest.resources.UpdateDeviceResource;

import java.util.UUID;

/**
 * Update Device Command From Resource Assembler
 */
public class UpdateDeviceCommandFromResourceAssembler {

    public static UpdateDeviceCommand toCommandFromResource(UUID deviceId, UpdateDeviceResource resource) {
        return new UpdateDeviceCommand(
                deviceId,
                resource.name(),
                resource.category(),
                resource.status(),
                resource.roomId(),
                resource.roomName(),
                resource.watts(),
                resource.voltage(),
                resource.amperage()
        );
    }
}