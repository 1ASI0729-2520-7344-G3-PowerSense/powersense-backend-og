package com.powersense.inventory.domain.model.aggregates;

import com.powersense.inventory.domain.model.commands.CreateDeviceCommand;
import com.powersense.inventory.domain.model.commands.UpdateDeviceCommand;
import com.powersense.inventory.domain.model.commands.UpdateDeviceStatusCommand;
import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;
import com.powersense.inventory.domain.model.valueobjects.Location;
import com.powersense.inventory.domain.model.valueobjects.PowerInfo;
import com.powersense.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * Device Aggregate Root
 * Represents an IoT device in the inventory
 */
@Entity
@Getter
public class Device extends AuditableAbstractAggregateRoot<Device> {

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private DeviceCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DeviceStatuses status;

    @Embedded
    private Location location;

    @Embedded
    private PowerInfo power;

    /**
     * Default constructor for JPA
     */
    protected Device() {
    }

    /**
     * Constructor from CreateDeviceCommand
     *
     * @param command CreateDeviceCommand
     */
    public Device(CreateDeviceCommand command) {
        this.name = command.name();
        this.category = command.category();
        this.status = command.status();
        this.location = new Location(command.roomId(), command.roomName());
        this.power = new PowerInfo(command.watts(), command.voltage(), command.amperage());
    }

    /**
     * Update device information
     *
     * @param command UpdateDeviceCommand
     * @return this Device instance
     */
    public Device update(UpdateDeviceCommand command) {
        this.name = command.name();
        this.category = command.category();
        this.status = command.status();
        this.location = new Location(command.roomId(), command.roomName());
        this.power = new PowerInfo(command.watts(), command.voltage(), command.amperage());
        return this;
    }

    /**
     * Update device status
     *
     * @param command UpdateDeviceStatusCommand
     * @return this Device instance
     */
    public Device updateStatus(UpdateDeviceStatusCommand command) {
        this.status = command.status();
        return this;
    }

    /**
     * Turn device ON
     */
    public Device turnOn() {
        this.status = DeviceStatuses.ACTIVE;
        return this;
    }

    /**
     * Turn device OFF
     */
    public Device turnOff() {
        this.status = DeviceStatuses.INACTIVE;
        return this;
    }

    /**
     * Toggle device status
     */
    public Device toggleStatus() {
        this.status = this.status.isActive() ? DeviceStatuses.INACTIVE : DeviceStatuses.ACTIVE;
        return this;
    }

    /**
     * Check if device is active
     */
    public boolean isActive() {
        return this.status.isActive();
    }

    /**
     * Check if device is inactive
     */
    public boolean isInactive() {
        return this.status.isInactive();
    }
}