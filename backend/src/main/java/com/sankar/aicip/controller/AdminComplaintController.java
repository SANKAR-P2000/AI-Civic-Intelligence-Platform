package com.sankar.aicip.controller;

import com.sankar.aicip.dto.request.ComplaintStatusUpdateRequest;
import com.sankar.aicip.dto.response.admin.AdminComplaintResponse;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.service.AdminComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Admin Complaint Management",
        description = "Administrative APIs for managing complaints"
)
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminComplaintController {

    private static final Logger logger =
            LoggerFactory.getLogger(AdminComplaintController.class);
    private final AdminComplaintService adminComplaintService;

    public AdminComplaintController(
            AdminComplaintService adminComplaintService) {

        this.adminComplaintService = adminComplaintService;
    }

    @Operation(
            summary = "Get All Complaints",
            description = "Returns every complaint in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaints retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/complaints")
    public ResponseEntity<List<AdminComplaintResponse>>
    getAllComplaints() {

        logger.info("Admin requested all complaints.");

        List<AdminComplaintResponse> response =
                adminComplaintService.getAllComplaints();

        logger.info("Returned {} complaints.",
                response.size());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Complaint By ID",
            description = "Returns a complaint using its unique ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaint found"),
            @ApiResponse(responseCode = "404", description = "Complaint not found")
    })
    @GetMapping("/complaints/{id}")
    public ResponseEntity<AdminComplaintResponse>
    getComplaintById(

            @Parameter(description = "Complaint ID")
            @PathVariable Long id) {

        logger.info("Admin requested complaint ID: {}",
                id);

        AdminComplaintResponse response =
                adminComplaintService.getComplaintById(id);

        logger.info("Complaint {} returned successfully.",
                id);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Filter Complaints By Status",
            description = "Returns complaints filtered by complaint status."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaints returned successfully")
    })
    @GetMapping("/complaints/status/{status}")
    public ResponseEntity<List<AdminComplaintResponse>>
    getComplaintsByStatus(

            @Parameter(description = "Complaint Status")
            @PathVariable ComplaintStatus status) {

        logger.info("Fetching complaints with status {}",
                status);

        List<AdminComplaintResponse> response =
                adminComplaintService.getComplaintsByStatus(status);

        logger.info("Returned {} complaints.",
                response.size());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Search Complaints",
            description = "Search complaints using a keyword."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    @GetMapping("/complaints/search")
    public ResponseEntity<List<AdminComplaintResponse>>
    searchComplaints(

            @Parameter(description = "Search keyword")
            @RequestParam String keyword) {

        logger.info("Searching complaints using keyword: {}",
                keyword);

        List<AdminComplaintResponse> response =
                adminComplaintService.searchComplaints(keyword);

        logger.info("Search returned {} complaints.",
                response.size());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update Complaint Status",
            description = "Updates the status of an existing complaint."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Complaint updated successfully"),
            @ApiResponse(responseCode = "404", description = "Complaint not found")
    })
    @PutMapping("/complaints/{complaintId}/status")
    public ResponseEntity<AdminComplaintResponse>
    updateComplaintStatus(

            @Parameter(description = "Complaint ID")
            @PathVariable Long complaintId,

            @Valid
            @RequestBody ComplaintStatusUpdateRequest request) {

        logger.info("Updating complaint {} to status {}",
                complaintId,
                request.getStatus());

        AdminComplaintResponse response =
                adminComplaintService.updateComplaintStatus(
                        complaintId,
                        request
                );

        logger.info("Complaint {} updated successfully.",
                complaintId);

        return ResponseEntity.ok(response);
    }

}