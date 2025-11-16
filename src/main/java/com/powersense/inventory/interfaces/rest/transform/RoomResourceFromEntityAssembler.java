package com.powersense.inventory.interfaces.rest.transform;

import com.powersense.inventory.domain.model.aggregates.Room;
import com.powersense.inventory.interfaces.rest.resources.RoomResource;

/**
 * Room Resource From Entity Assembler
 * Transforms Room entity to RoomResource
 */
public class RoomResourceFromEntityAssembler {

    /**
     * Transform Room entity to RoomResource
     *
     * @param entity Room entity
     * @return RoomResource
     */
    public static RoomResource toResourceFromEntity(Room entity) {
        return new RoomResource(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}