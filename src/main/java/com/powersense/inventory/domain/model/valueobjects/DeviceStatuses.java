package com.powersense.inventory.domain.model.valueobjects;

/**
 * Device Statuses
 * Enum representing the operational status of a device
 */
public enum DeviceStatuses {
    ACTIVE("Active", "Device is currently ON"),
    INACTIVE("Inactive", "Device is currently OFF");

    private final String displayName;
    private final String description;

    DeviceStatuses(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Check if device is active
     */
    public boolean isActive() {
        return this == ACTIVE;
    }

    /**
     * Check if device is inactive
     */
    public boolean isInactive() {
        return this == INACTIVE;
    }
}