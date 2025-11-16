package com.powersense.inventory.infrastructure.persistence.jpa.repositories;

import com.powersense.inventory.domain.model.aggregates.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Room Repository
 * JPA Repository for Room aggregate
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    /**
     * Check if a room exists by name
     *
     * @param name Room name
     * @return true if exists, false otherwise
     */
    boolean existsByName(String name);

    /**
     * Find room by name
     *
     * @param name Room name
     * @return Optional containing the room if found
     */
    Optional<Room> findByName(String name);
}