package com.sankar.aicip.controller;

import com.sankar.aicip.dto.request.CreateComplaintRequest;
import com.sankar.aicip.dto.response.ComplaintResponse;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Complaint Management",
        description = "APIs for creating, tracking, viewing, and updating complaints"
)
@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private static final Logger logger =
            LoggerFactory.getLogger(ComplaintController.class);

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @Operation(
            summary = "Create Complaint",
            description = "Allows a citizen to create a new complaint."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Complaint created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    @PreAuthorize("hasRole('CITIZEN')")
    public ComplaintResponse createComplaint(
            @Valid @RequestBody CreateComplaintRequest request) {

        logger.info("Complaint creation request received.");

        ComplaintResponse response = complaintService.createComplaint(request);

        logger.info("Complaint created successfully. Complaint ID: {}",
                response.getId());

        return response;
    }

    @Operation(
            summary = "Get My Complaints",
            description = "Returns all complaints submitted by the authenticated citizen."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaints retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/my")
    @PreAuthorize("hasRole('CITIZEN')")
    public List<ComplaintResponse> getMyComplaints() {

        logger.info("Fetching logged-in user's complaints.");

        List<ComplaintResponse> complaints =
                complaintService.getMyComplaints();

        logger.info("Returned {} complaints.",
                complaints.size());

        return complaints;
    }

    @Operation(
            summary = "Get All Complaints",
            description = "Returns all complaints for administrators and government officers."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaints retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
    public List<ComplaintResponse> getAllComplaints() {

        logger.info("Fetching all complaints.");

        List<ComplaintResponse> complaints =
                complaintService.getAllComplaints();

        logger.info("Returned {} complaints.",
                complaints.size());

        return complaints;
    }

    @Operation(
            summary = "Update Complaint Status",
            description = "Updates the status of an existing complaint."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaint status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Complaint not found")
    })
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
    public ComplaintResponse updateComplaintStatus(
            @Parameter(description = "Complaint ID")
            @PathVariable Long id,

            @Parameter(description = "New complaint status")
            @RequestParam ComplaintStatus status) {

        logger.info("Updating complaint {} to status {}",
                id, status);

        ComplaintResponse response =
                complaintService.updateComplaintStatus(id, status);

        logger.info("Complaint {} updated successfully.",
                id);

        return response;
    }

    @Operation(
            summary = "Track Complaint",
            description = "Returns the complete details of a complaint by its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaint details returned successfully"),
            @ApiResponse(responseCode = "404", description = "Complaint not found")
    })
    @GetMapping("/track/{id}")
    @PreAuthorize("isAuthenticated()")
    public ComplaintResponse trackComplaint(
            @Parameter(description = "Complaint ID")
            @PathVariable Long id) {

        logger.info("Tracking complaint ID: {}", id);

        ComplaintResponse response =
                complaintService.trackComplaint(id);

        logger.info("Complaint {} details returned successfully.",
                id);

        return response;
    }

}