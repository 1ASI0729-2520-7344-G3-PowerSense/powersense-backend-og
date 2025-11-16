package com.powersense.inventory.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

/**
 * Location Value Object
 * Represents the physical location of a device (room)
 */
@Embeddable
@Getter
public class Location {

    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @Column(name = "room_name", nullable = false, length = 100)
    private String roomName;

    /**
     * Default constructor for JPA
     */
    protected Location() {
    }

    /**
     * Constructor
     *
     * @param roomId Room identifier
     * @param roomName Room name
     */
    public Location(UUID roomId, String roomName) {
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        if (roomName == null || roomName.isBlank()) {
            throw new IllegalArgumentException("Room name cannot be null or empty");
        }
        this.roomId = roomId;
        this.roomName = roomName;
    }

    /**
     * Update location with new room information
     */
    public Location update(UUID roomId, String roomName) {
        return new Location(roomId, roomName);
    }
}