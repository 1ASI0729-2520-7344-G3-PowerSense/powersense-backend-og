package com.powersense.inventory.infrastructure.persistence.jpa.repositories;

import com.powersense.inventory.domain.model.aggregates.Device;
import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Device Repository
 * JPA Repository for Device aggregate
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    /**
     * Find devices by room ID
     */
    @Query("SELECT d FROM Device d WHERE d.location.roomId = :roomId")
    List<Device> findByLocationRoomId(@Param("roomId") UUID roomId);

    /**
     * Find devices by category
     */
    List<Device> findByCategory(DeviceCategory category);

    /**
     * Find devices by status
     */
    List<Device> findByStatus(DeviceStatuses status);

    /**
     * Find devices by search term (name or room name)
     */
    @Query("SELECT d FROM Device d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.location.roomName) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Device> findBySearchTerm(@Param("search") String search);

    /**
     * Find devices with all filters
     */
    @Query("SELECT d FROM Device d WHERE " +
            "(:search IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(d.location.roomName) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:category IS NULL OR d.category = :category) AND " +
            "(:roomId IS NULL OR d.location.roomId = :roomId) AND " +
            "(:status IS NULL OR d.status = :status)")
    List<Device> findByFilters(
            @Param("search") String search,
            @Param("category") DeviceCategory category,
            @Param("roomId") UUID roomId,
            @Param("status") DeviceStatuses status
    );

    /**
     * Check if device name exists
     */
    boolean existsByName(String name);

    /**
     * Count devices by room
     */
    @Query("SELECT COUNT(d) FROM Device d WHERE d.location.roomId = :roomId")
    long countByRoomId(@Param("roomId") UUID roomId);
}