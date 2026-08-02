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

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private static final Logger logger =
            LoggerFactory.getLogger(ComplaintController.class);

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

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

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
    public ComplaintResponse updateComplaintStatus(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status) {

        logger.info("Updating complaint {} to status {}",
                id, status);

        ComplaintResponse response =
                complaintService.updateComplaintStatus(id, status);

        logger.info("Complaint {} updated successfully.",
                id);

        return response;
    }
    @GetMapping("/track/{id}")
    @PreAuthorize("isAuthenticated()")
    public ComplaintResponse trackComplaint(@PathVariable Long id) {

        logger.info("Tracking complaint ID: {}", id);

        ComplaintResponse response =
                complaintService.trackComplaint(id);

        logger.info("Complaint {} details returned successfully.",
                id);

        return response;
    }

}