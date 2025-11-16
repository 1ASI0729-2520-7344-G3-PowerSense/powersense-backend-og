package com.powersense.inventory.interfaces.rest.transform;

import com.powersense.inventory.domain.model.commands.BulkUpdateDeviceStatusCommand;
import com.powersense.inventory.interfaces.rest.resources.BulkUpdateDeviceStatusResource;

/**
 * Bulk Update Device Status Command From Resource Assembler
 */
public class BulkUpdateDeviceStatusCommandFromResourceAssembler {

    public static BulkUpdateDeviceStatusCommand toCommandFromResource(BulkUpdateDeviceStatusResource resource) {
        return new BulkUpdateDeviceStatusCommand(resource.deviceIds(), resource.status());
    }
}