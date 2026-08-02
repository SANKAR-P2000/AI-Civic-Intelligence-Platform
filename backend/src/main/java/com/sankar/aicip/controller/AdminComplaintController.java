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

import java.util.List;

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

    @GetMapping("/complaints/{id}")
    public ResponseEntity<AdminComplaintResponse>
    getComplaintById(
            @PathVariable Long id) {

        logger.info("Admin requested complaint ID: {}",
                id);

        AdminComplaintResponse response =
                adminComplaintService.getComplaintById(id);

        logger.info("Complaint {} returned successfully.",
                id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/complaints/status/{status}")
    public ResponseEntity<List<AdminComplaintResponse>>
    getComplaintsByStatus(
            @PathVariable ComplaintStatus status) {

        logger.info("Fetching complaints with status {}",
                status);

        List<AdminComplaintResponse> response =
                adminComplaintService.getComplaintsByStatus(status);

        logger.info("Returned {} complaints.",
                response.size());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/complaints/search")
    public ResponseEntity<List<AdminComplaintResponse>>
    searchComplaints(
            @RequestParam String keyword) {

        logger.info("Searching complaints using keyword: {}",
                keyword);

        List<AdminComplaintResponse> response =
                adminComplaintService.searchComplaints(keyword);

        logger.info("Search returned {} complaints.",
                response.size());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/complaints/{complaintId}/status")
    public ResponseEntity<AdminComplaintResponse>
    updateComplaintStatus(
            @PathVariable Long complaintId,
            @Valid @RequestBody ComplaintStatusUpdateRequest request) {

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