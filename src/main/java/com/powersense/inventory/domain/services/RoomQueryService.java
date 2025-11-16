package com.powersense.inventory.domain.services;

import com.powersense.inventory.domain.model.aggregates.Room;
import com.powersense.inventory.domain.model.queries.GetAllRoomsQuery;
import com.powersense.inventory.domain.model.queries.GetRoomByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Room Query Service
 * Port (interface) for room read operations
 */
public interface RoomQueryService {

    /**
     * Handle GetAllRoomsQuery
     *
     * @param query GetAllRoomsQuery
     * @return List of all rooms
     */
    List<Room> handle(GetAllRoomsQuery query);

    /**
     * Handle GetRoomByIdQuery
     *
     * @param query GetRoomByIdQuery
     * @return Optional containing the room if found
     */
    Optional<Room> handle(GetRoomByIdQuery query);
}