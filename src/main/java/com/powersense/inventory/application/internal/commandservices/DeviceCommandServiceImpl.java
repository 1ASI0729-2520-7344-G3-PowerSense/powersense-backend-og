package com.powersense.inventory.application.internal.commandservices;

import com.powersense.inventory.domain.model.aggregates.Device;
import com.powersense.inventory.domain.model.aggregates.Room;
import com.powersense.inventory.domain.model.commands.BulkUpdateDeviceStatusCommand;
import com.powersense.inventory.domain.model.commands.CreateDeviceCommand;
import com.powersense.inventory.domain.model.commands.UpdateDeviceCommand;
import com.powersense.inventory.domain.model.commands.UpdateDeviceStatusCommand;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;
import com.powersense.inventory.domain.services.DeviceCommandService;
import com.powersense.inventory.infrastructure.persistence.jpa.repositories.DeviceRepository;
import com.powersense.inventory.infrastructure.persistence.jpa.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Device Command Service Implementation
 */
@Service
public class DeviceCommandServiceImpl implements DeviceCommandService {

    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;

    public DeviceCommandServiceImpl(DeviceRepository deviceRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Device handle(CreateDeviceCommand command) {
        // Validate room exists
        Room room = roomRepository.findById(command.roomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + command.roomId()));

        // Validate device name doesn't exist
        if (deviceRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Device with name '" + command.name() + "' already exists");
        }

        // Create and save device
        Device device = new Device(command);
        return deviceRepository.save(device);
    }

    @Override
    @Transactional
    public Device handle(UpdateDeviceCommand command) {
        // Find device
        Device device = deviceRepository.findById(command.deviceId())
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + command.deviceId()));

        // Validate room exists
        Room room = roomRepository.findById(command.roomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + command.roomId()));

        // Validate new name doesn't exist (if changed)
        if (!device.getName().equals(command.name()) && deviceRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Device with name '" + command.name() + "' already exists");
        }

        // Update and save
        device.update(command);
        return deviceRepository.save(device);
    }

    @Override
    @Transactional
    public Device handle(UpdateDeviceStatusCommand command) {
        Device device = deviceRepository.findById(command.deviceId())
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + command.deviceId()));

        device.updateStatus(command);
        return deviceRepository.save(device);
    }

    @Override
    @Transactional
    public List<Device> handle(BulkUpdateDeviceStatusCommand command) {
        List<Device> devices = deviceRepository.findAllById(command.deviceIds());

        if (devices.size() != command.deviceIds().size()) {
            throw new IllegalArgumentException("Some devices were not found");
        }

        devices.forEach(device -> {
            if (command.status().isActive()) {
                device.turnOn();
            } else {
                device.turnOff();
            }
        });

        return deviceRepository.saveAll(devices);
    }

    @Override
    @Transactional
    public List<Device> updateRoomDevicesStatus(UUID roomId, DeviceStatuses status) {
        // Validate room exists
        roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));

        List<Device> devices = deviceRepository.findByLocationRoomId(roomId);

        devices.forEach(device -> {
            if (status.isActive()) {
                device.turnOn();
            } else {
                device.turnOff();
            }
        });

        return deviceRepository.saveAll(devices);
    }

    @Override
    @Transactional
    public void deleteById(UUID deviceId) {
        if (!deviceRepository.existsById(deviceId)) {
            throw new IllegalArgumentException("Device not found with id: " + deviceId);
        }
        deviceRepository.deleteById(deviceId);
    }
}