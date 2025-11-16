package com.powersense.inventory.interfaces.rest;

import com.powersense.inventory.domain.model.aggregates.Device;
import com.powersense.inventory.domain.model.commands.*;
import com.powersense.inventory.domain.model.queries.*;
import com.powersense.inventory.domain.model.valueobjects.DeviceCategory;
import com.powersense.inventory.domain.model.valueobjects.DeviceStatuses;
import com.powersense.inventory.domain.services.DeviceCommandService;
import com.powersense.inventory.domain.services.DeviceQueryService;
import com.powersense.inventory.interfaces.rest.resources.*;
import com.powersense.inventory.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
 * Devices Controller
 * REST API for device management
 */
@RestController
@RequestMapping("/devices")
@Tag(name = "Devices", description = "Device management endpoints")
public class DevicesController {

    private final DeviceCommandService deviceCommandService;
    private final DeviceQueryService deviceQueryService;

    public DevicesController(DeviceCommandService deviceCommandService, DeviceQueryService deviceQueryService) {
        this.deviceCommandService = deviceCommandService;
        this.deviceQueryService = deviceQueryService;
    }

    /**
     * Create a new device
     */
    @PostMapping
    @Operation(summary = "Create a new device", description = "Creates a new IoT device in the inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    public ResponseEntity<DeviceResource> createDevice(@Valid @RequestBody CreateDeviceResource resource) {
        CreateDeviceCommand command = CreateDeviceCommandFromResourceAssembler.toCommandFromResource(resource);
        Device device = deviceCommandService.handle(command);
        DeviceResource deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device);
        return new ResponseEntity<>(deviceResource, HttpStatus.CREATED);
    }

    /**
     * Get all devices with optional filters
     */
    @GetMapping
    @Operation(summary = "Get all devices", description = "Retrieves all devices with optional filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices retrieved successfully")
    })
    public ResponseEntity<List<DeviceResource>> getAllDevices(
            @Parameter(description = "Search term (searches in device name and room name)")
            @RequestParam(required = false) String search,

            @Parameter(description = "Filter by device category")
            @RequestParam(required = false) DeviceCategory category,

            @Parameter(description = "Filter by room ID")
            @RequestParam(required = false) UUID roomId,

            @Parameter(description = "Filter by device status")
            @RequestParam(required = false) DeviceStatuses status
    ) {
        GetAllDevicesQuery query = new GetAllDevicesQuery(search, category, roomId, status);
        List<Device> devices = deviceQueryService.handle(query);
        List<DeviceResource> deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }

    /**
     * Get device by ID
     */
    @GetMapping("/{deviceId}")
    @Operation(summary = "Get device by ID", description = "Retrieves a specific device by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device found"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    public ResponseEntity<DeviceResource> getDeviceById(@PathVariable UUID deviceId) {
        GetDeviceByIdQuery query = new GetDeviceByIdQuery(deviceId);
        Device device = deviceQueryService.handle(query)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + deviceId));
        DeviceResource deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device);
        return ResponseEntity.ok(deviceResource);
    }

    /**
     * Get devices by room ID
     */
    @GetMapping("/room/{roomId}")
    @Operation(summary = "Get devices by room", description = "Retrieves all devices in a specific room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices retrieved successfully")
    })
    public ResponseEntity<List<DeviceResource>> getDevicesByRoomId(@PathVariable UUID roomId) {
        GetDevicesByRoomIdQuery query = new GetDevicesByRoomIdQuery(roomId);
        List<Device> devices = deviceQueryService.handle(query);
        List<DeviceResource> deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }

    /**
     * Get devices by category
     */
    @GetMapping("/category/{category}")
    @Operation(summary = "Get devices by category", description = "Retrieves all devices of a specific category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices retrieved successfully")
    })
    public ResponseEntity<List<DeviceResource>> getDevicesByCategory(@PathVariable DeviceCategory category) {
        GetDevicesByCategoryQuery query = new GetDevicesByCategoryQuery(category);
        List<Device> devices = deviceQueryService.handle(query);
        List<DeviceResource> deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }

    /**
     * Get devices by status
     */
    @GetMapping("/status/{status}")
    @Operation(summary = "Get devices by status", description = "Retrieves all devices with a specific status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices retrieved successfully")
    })
    public ResponseEntity<List<DeviceResource>> getDevicesByStatus(@PathVariable DeviceStatuses status) {
        GetDevicesByStatusQuery query = new GetDevicesByStatusQuery(status);
        List<Device> devices = deviceQueryService.handle(query);
        List<DeviceResource> deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }

    /**
     * Update a device
     */
    @PatchMapping("/{deviceId}")
    @Operation(summary = "Update a device", description = "Updates an existing device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device updated successfully"),
            @ApiResponse(responseCode = "404", description = "Device not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<DeviceResource> updateDevice(
            @PathVariable UUID deviceId,
            @Valid @RequestBody UpdateDeviceResource resource
    ) {
        UpdateDeviceCommand command = UpdateDeviceCommandFromResourceAssembler.toCommandFromResource(deviceId, resource);
        Device device = deviceCommandService.handle(command);
        DeviceResource deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device);
        return ResponseEntity.ok(deviceResource);
    }

    /**
     * Update device status
     */
    @PatchMapping("/{deviceId}/status")
    @Operation(summary = "Update device status", description = "Updates the status of a device (ACTIVE/INACTIVE)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    public ResponseEntity<DeviceResource> updateDeviceStatus(
            @PathVariable UUID deviceId,
            @Valid @RequestBody UpdateDeviceStatusResource resource
    ) {
        UpdateDeviceStatusCommand command = UpdateDeviceStatusCommandFromResourceAssembler
                .toCommandFromResource(deviceId, resource);
        Device device = deviceCommandService.handle(command);
        DeviceResource deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device);
        return ResponseEntity.ok(deviceResource);
    }

    /**
     * Bulk update device status
     */
    @PostMapping("/bulk-status")
    @Operation(summary = "Bulk update device status", description = "Updates the status of multiple devices at once")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devices status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Some devices not found")
    })
    public ResponseEntity<List<DeviceResource>> bulkUpdateDeviceStatus(
            @Valid @RequestBody BulkUpdateDeviceStatusResource resource
    ) {
        BulkUpdateDeviceStatusCommand command = BulkUpdateDeviceStatusCommandFromResourceAssembler
                .toCommandFromResource(resource);
        List<Device> devices = deviceCommandService.handle(command);
        List<DeviceResource> deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }

    /**
     * Update all devices in a room
     */
    @PostMapping("/room/{roomId}/status")
    @Operation(summary = "Update room devices status", description = "Updates the status of all devices in a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room devices status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    public ResponseEntity<List<DeviceResource>> updateRoomDevicesStatus(
            @PathVariable UUID roomId,
            @Valid @RequestBody UpdateDeviceStatusResource resource
    ) {
        List<Device> devices = deviceCommandService.updateRoomDevicesStatus(roomId, resource.status());
        List<DeviceResource> deviceResources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(deviceResources);
    }

    /**
     * Delete a device
     */
    @DeleteMapping("/{deviceId}")
    @Operation(summary = "Delete a device", description = "Deletes a device from the inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Device deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID deviceId) {
        deviceCommandService.deleteById(deviceId);
        return ResponseEntity.noContent().build();
    }
}