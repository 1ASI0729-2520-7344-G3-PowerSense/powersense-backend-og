package com.powersense.inventory.application.internal.queryservices;

import com.powersense.inventory.domain.model.aggregates.Device;
import com.powersense.inventory.domain.model.queries.*;
import com.powersense.inventory.domain.services.DeviceQueryService;
import com.powersense.inventory.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Device Query Service Implementation
 */
@Service
@Transactional(readOnly = true)
public class DeviceQueryServiceImpl implements DeviceQueryService {

    private final DeviceRepository deviceRepository;

    public DeviceQueryServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> handle(GetAllDevicesQuery query) {
        // If no filters, return all
        if (query.search() == null && query.category() == null &&
                query.roomId() == null && query.status() == null) {
            return deviceRepository.findAll();
        }

        // Apply filters
        return deviceRepository.findByFilters(
                query.search(),
                query.category(),
                query.roomId(),
                query.status()
        );
    }

    @Override
    public Optional<Device> handle(GetDeviceByIdQuery query) {
        return deviceRepository.findById(query.deviceId());
    }

    @Override
    public List<Device> handle(GetDevicesByRoomIdQuery query) {
        return deviceRepository.findByLocationRoomId(query.roomId());
    }

    @Override
    public List<Device> handle(GetDevicesByCategoryQuery query) {
        return deviceRepository.findByCategory(query.category());
    }

    @Override
    public List<Device> handle(GetDevicesByStatusQuery query) {
        return deviceRepository.findByStatus(query.status());
    }
}