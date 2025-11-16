package com.powersense.inventory.application.internal.queryservices;

import com.powersense.inventory.domain.model.aggregates.Room;
import com.powersense.inventory.domain.model.queries.GetAllRoomsQuery;
import com.powersense.inventory.domain.model.queries.GetRoomByIdQuery;
import com.powersense.inventory.domain.services.RoomQueryService;
import com.powersense.inventory.infrastructure.persistence.jpa.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Room Query Service Implementation
 * Handles room read operations
 */
@Service
@Transactional(readOnly = true)
public class RoomQueryServiceImpl implements RoomQueryService {

    private final RoomRepository roomRepository;

    public RoomQueryServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> handle(GetAllRoomsQuery query) {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> handle(GetRoomByIdQuery query) {
        return roomRepository.findById(query.roomId());
    }
}