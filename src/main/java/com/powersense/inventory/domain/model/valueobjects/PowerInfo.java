package com.powersense.inventory.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * Power Info Value Object
 * Represents the power consumption information of a device
 */
@Embeddable
@Getter
public class PowerInfo {

    @Column(name = "power_watts", nullable = false)
    private Double watts;

    @Column(name = "power_voltage")
    private Double voltage;

    @Column(name = "power_amperage")
    private Double amperage;

    /**
     * Default constructor for JPA
     */
    protected PowerInfo() {
    }

    /**
     * Constructor with watts only
     *
     * @param watts Power consumption in watts
     */
    public PowerInfo(Double watts) {
        this(watts, null, null);
    }

    /**
     * Full constructor
     *
     * @param watts Power consumption in watts
     * @param voltage Voltage in volts
     * @param amperage Current in amperes
     */
    public PowerInfo(Double watts, Double voltage, Double amperage) {
        if (watts == null || watts < 0) {
            throw new IllegalArgumentException("Watts must be a positive number");
        }
        if (voltage != null && voltage < 0) {
            throw new IllegalArgumentException("Voltage must be a positive number");
        }
        if (amperage != null && amperage < 0) {
            throw new IllegalArgumentException("Amperage must be a positive number");
        }
        this.watts = watts;
        this.voltage = voltage;
        this.amperage = amperage;
    }

    /**
     * Update watts
     */
    public PowerInfo updateWatts(Double watts) {
        return new PowerInfo(watts, this.voltage, this.amperage);
    }

    /**
     * Calculate kilowatts
     */
    public Double getKilowatts() {
        return watts / 1000.0;
    }
}