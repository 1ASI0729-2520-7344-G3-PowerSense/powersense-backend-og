package com.powersense.inventory.domain.model.aggregates;

import com.powersense.inventory.domain.model.commands.CreateRoomCommand;
import com.powersense.inventory.domain.model.commands.UpdateRoomCommand;
import com.powersense.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

/**
 * Room Aggregate Root
 * Represents a physical location/room where devices can be placed
 */
@Entity
@Getter
public class Room extends AuditableAbstractAggregateRoot<Room> {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    /**
     * Default constructor for JPA
     */
    protected Room() {
    }

    /**
     * Constructor from CreateRoomCommand
     *
     * @param command CreateRoomCommand with room data
     */
    public Room(CreateRoomCommand command) {
        this.name = command.name();
    }

    /**
     * Update room information
     *
     * @param command UpdateRoomCommand with new data
     * @return this Room instance for method chaining
     */
    public Room update(UpdateRoomCommand command) {
        this.name = command.name();
        return this;
    }
}