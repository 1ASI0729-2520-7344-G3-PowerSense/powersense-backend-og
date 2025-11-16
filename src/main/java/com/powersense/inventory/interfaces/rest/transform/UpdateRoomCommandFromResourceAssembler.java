package com.powersense.inventory.interfaces.rest.transform;

import com.powersense.inventory.domain.model.commands.UpdateRoomCommand;
import com.powersense.inventory.interfaces.rest.resources.UpdateRoomResource;

import java.util.UUID;

/**
 * Update Room Command From Resource Assembler
 * Transforms UpdateRoomResource to UpdateRoomCommand
 */
public class UpdateRoomCommandFromResourceAssembler {

    /**
     * Transform UpdateRoomResource to UpdateRoomCommand
     *
     * @param roomId Room identifier
     * @param resource UpdateRoomResource
     * @return UpdateRoomCommand
     */
    public static UpdateRoomCommand toCommandFromResource(UUID roomId, UpdateRoomResource resource) {
        return new UpdateRoomCommand(roomId, resource.name());
    }
}