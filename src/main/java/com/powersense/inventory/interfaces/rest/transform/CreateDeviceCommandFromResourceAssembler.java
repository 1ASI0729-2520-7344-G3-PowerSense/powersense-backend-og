package com.powersense.inventory.interfaces.rest.transform;

import com.powersense.inventory.domain.model.commands.CreateDeviceCommand;
import com.powersense.inventory.interfaces.rest.resources.CreateDeviceResource;

/**
 * Create Device Command From Resource Assembler
 */
public class CreateDeviceCommandFromResourceAssembler {

    public static CreateDeviceCommand toCommandFromResource(CreateDeviceResource resource) {
        return new CreateDeviceCommand(
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