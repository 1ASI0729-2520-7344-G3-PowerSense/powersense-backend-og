package com.powersense.inventory.application.internal.commandservices;

import com.powersense.inventory.domain.model.aggregates.Room;
import com.powersense.inventory.domain.model.commands.CreateRoomCommand;
import com.powersense.inventory.domain.model.commands.UpdateRoomCommand;
import com.powersense.inventory.domain.services.RoomCommandService;
import com.powersense.inventory.infrastructure.persistence.jpa.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Room Command Service Implementation
 * Handles room write operations
 */
@Service
public class RoomCommandServiceImpl implements RoomCommandService {

    private final RoomRepository roomRepository;

    public RoomCommandServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Room handle(CreateRoomCommand command) {
        // Validate room name doesn't exist
        if (roomRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Room with name '" + command.name() + "' already exists");
        }

        // Create and save room
        Room room = new Room(command);
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public Room handle(UpdateRoomCommand command) {
        // Find room
        Room room = roomRepository.findById(command.roomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + command.roomId()));

        // Validate new name doesn't exist (if changed)
        if (!room.getName().equals(command.name()) && roomRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Room with name '" + command.name() + "' already exists");
        }

        // Update and save
        room.update(command);
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public void deleteById(UUID roomId) {
        // Verify room exists
        if (!roomRepository.existsById(roomId)) {
            throw new IllegalArgumentException("Room not found with id: " + roomId);
        }

        // Delete room
        roomRepository.deleteById(roomId);
    }
}