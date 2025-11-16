package com.powersense.inventory.interfaces.rest.transform;

import com.powersense.inventory.domain.model.aggregates.Device;
import com.powersense.inventory.interfaces.rest.resources.DeviceResource;

/**
 * Device Resource From Entity Assembler
 */
public class DeviceResourceFromEntityAssembler {

    public static DeviceResource toResourceFromEntity(Device entity) {
        DeviceResource.LocationResource location = new DeviceResource.LocationResource(
                entity.getLocation().getRoomId(),
                entity.getLocation().getRoomName()
        );

        DeviceResource.PowerInfoResource power = new DeviceResource.PowerInfoResource(
                entity.getPower().getWatts(),
                entity.getPower().getVoltage(),
                entity.getPower().getAmperage(),
                entity.getPower().getKilowatts()
        );

        return new DeviceResource(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getStatus(),
                location,
                power,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}