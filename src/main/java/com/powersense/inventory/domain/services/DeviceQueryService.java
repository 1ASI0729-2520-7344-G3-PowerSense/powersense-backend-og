package com.powersense.inventory.domain.services;

import com.powersense.inventory.domain.model.aggregates.Device;
import com.powersense.inventory.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Device Query Service
 * Port (interface) for device read operations
 */
public interface DeviceQueryService {

    /**
     * Handle GetAllDevicesQuery
     */
    List<Device> handle(GetAllDevicesQuery query);

    /**
     * Handle GetDeviceByIdQuery
     */
    Optional<Device> handle(GetDeviceByIdQuery query);

    /**
     * Handle GetDevicesByRoomIdQuery
     */
    List<Device> handle(GetDevicesByRoomIdQuery query);

    /**
     * Handle GetDevicesByCategoryQuery
     */
    List<Device> handle(GetDevicesByCategoryQuery query);

    /**
     * Handle GetDevicesByStatusQuery
     */
    List<Device> handle(GetDevicesByStatusQuery query);
}