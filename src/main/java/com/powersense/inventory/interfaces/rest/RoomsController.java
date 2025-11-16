package com.powersense.inventory.interfaces.rest;

import com.powersense.inventory.domain.model.aggregates.Room;
import com.powersense.inventory.domain.model.commands.CreateRoomCommand;
import com.powersense.inventory.domain.model.commands.UpdateRoomCommand;
import com.powersense.inventory.domain.model.queries.GetAllRoomsQuery;
import com.powersense.inventory.domain.model.queries.GetRoomByIdQuery;
import com.powersense.inventory.domain.services.RoomCommandService;
import com.powersense.inventory.domain.services.RoomQueryService;
import com.powersense.inventory.interfaces.rest.resources.CreateRoomResource;
import com.powersense.inventory.interfaces.rest.resources.RoomResource;
import com.powersense.inventory.interfaces.rest.resources.UpdateRoomResource;
import com.powersense.inventory.interfaces.rest.transform.CreateRoomCommandFromResourceAssembler;
import com.powersense.inventory.interfaces.rest.transform.RoomResourceFromEntityAssembler;
import com.powersense.inventory.interfaces.rest.transform.UpdateRoomCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Rooms Controller
 * REST API for room management
 */
@RestController
@RequestMapping("/rooms")
@Tag(name = "Rooms", description = "Room management endpoints")
public class RoomsController {

    private final RoomCommandService roomCommandService;
    private final RoomQueryService roomQueryService;

    public RoomsController(RoomCommandService roomCommandService, RoomQueryService roomQueryService) {
        this.roomCommandService = roomCommandService;
        this.roomQueryService = roomQueryService;
    }

    /**
     * Create a new room
     *
     * @param resource CreateRoomResource
     * @return Created RoomResource
     */
    @PostMapping
    @Operation(summary = "Create a new room", description = "Creates a new room in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Room created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<RoomResource> createRoom(@Valid @RequestBody CreateRoomResource resource) {
        CreateRoomCommand command = CreateRoomCommandFromResourceAssembler.toCommandFromResource(resource);
        Room room = roomCommandService.handle(command);
        RoomResource roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(room);
        return new ResponseEntity<>(roomResource, HttpStatus.CREATED);
    }

    /**
     * Get all rooms
     *
     * @return List of RoomResource
     */
    @GetMapping
    @Operation(summary = "Get all rooms", description = "Retrieves all rooms in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rooms retrieved successfully")
    })
    public ResponseEntity<List<RoomResource>> getAllRooms() {
        GetAllRoomsQuery query = new GetAllRoomsQuery();
        List<Room> rooms = roomQueryService.handle(query);
        List<RoomResource> roomResources = rooms.stream()
                .map(RoomResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(roomResources);
    }

    /**
     * Get room by ID
     *
     * @param roomId Room identifier
     * @return RoomResource
     */
    @GetMapping("/{roomId}")
    @Operation(summary = "Get room by ID", description = "Retrieves a specific room by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room found"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    public ResponseEntity<RoomResource> getRoomById(@PathVariable UUID roomId) {
        GetRoomByIdQuery query = new GetRoomByIdQuery(roomId);
        Room room = roomQueryService.handle(query)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));
        RoomResource roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(room);
        return ResponseEntity.ok(roomResource);
    }

    /**
     * Update a room
     *
     * @param roomId Room identifier
     * @param resource UpdateRoomResource
     * @return Updated RoomResource
     */
    @PatchMapping("/{roomId}")
    @Operation(summary = "Update a room", description = "Updates an existing room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room updated successfully"),
            @ApiResponse(responseCode = "404", description = "Room not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<RoomResource> updateRoom(
            @PathVariable UUID roomId,
            @Valid @RequestBody UpdateRoomResource resource) {
        UpdateRoomCommand command = UpdateRoomCommandFromResourceAssembler.toCommandFromResource(roomId, resource);
        Room room = roomCommandService.handle(command);
        RoomResource roomResource = RoomResourceFromEntityAssembler.toResourceFromEntity(room);
        return ResponseEntity.ok(roomResource);
    }

    /**
     * Delete a room
     *
     * @param roomId Room identifier
     * @return No content
     */
    @DeleteMapping("/{roomId}")
    @Operation(summary = "Delete a room", description = "Deletes a room from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Room deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID roomId) {
        roomCommandService.deleteById(roomId);
        return ResponseEntity.noContent().build();
    }
}