package com.powersense.inventory.interfaces.rest.transform;

import com.powersense.inventory.domain.model.commands.CreateRoomCommand;
import com.powersense.inventory.interfaces.rest.resources.CreateRoomResource;

/**
 * Create Room Command From Resource Assembler
 * Transforms CreateRoomResource to CreateRoomCommand
 */
public class CreateRoomCommandFromResourceAssembler {

    /**
     * Transform CreateRoomResource to CreateRoomCommand
     *
     * @param resource CreateRoomResource
     * @return CreateRoomCommand
     */
    public static CreateRoomCommand toCommandFromResource(CreateRoomResource resource) {
        return new CreateRoomCommand(resource.name());
    }
}