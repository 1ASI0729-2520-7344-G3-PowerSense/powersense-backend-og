package com.powersense.inventory.interfaces.rest.transform;

import com.powersense.inventory.domain.model.commands.UpdateDeviceStatusCommand;
import com.powersense.inventory.interfaces.rest.resources.UpdateDeviceStatusResource;

import java.util.UUID;

/**
 * Update Device Status Command From Resource Assembler
 */
public class UpdateDeviceStatusCommandFromResourceAssembler {

    public static UpdateDeviceStatusCommand toCommandFromResource(UUID deviceId, UpdateDeviceStatusResource resource) {
        return new UpdateDeviceStatusCommand(deviceId, resource.status());
    }
}