package com.sankar.aicip.controller;

import com.sankar.aicip.dto.request.ComplaintStatusUpdateRequest;
import com.sankar.aicip.dto.response.admin.AdminComplaintResponse;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.service.AdminComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminComplaintController {

    private final AdminComplaintService adminComplaintService;

    public AdminComplaintController(
            AdminComplaintService adminComplaintService) {

        this.adminComplaintService = adminComplaintService;
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<AdminComplaintResponse>>
    getAllComplaints() {

        return ResponseEntity.ok(
                adminComplaintService.getAllComplaints()
        );
    }

    @GetMapping("/complaints/{id}")
    public ResponseEntity<AdminComplaintResponse>
    getComplaintById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminComplaintService.getComplaintById(id)
        );
    }

    @GetMapping("/complaints/status/{status}")
    public ResponseEntity<List<AdminComplaintResponse>>
    getComplaintsByStatus(
            @PathVariable ComplaintStatus status) {

        return ResponseEntity.ok(
                adminComplaintService
                        .getComplaintsByStatus(status)
        );
    }

    @GetMapping("/complaints/search")
    public ResponseEntity<List<AdminComplaintResponse>>
    searchComplaints(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                adminComplaintService.searchComplaints(keyword)
        );
    }

    @PutMapping("/complaints/{complaintId}/status")
    public ResponseEntity<AdminComplaintResponse> updateComplaintStatus(
            @PathVariable Long complaintId,
            @Valid @RequestBody ComplaintStatusUpdateRequest request) {

        return ResponseEntity.ok(
                adminComplaintService.updateComplaintStatus(
                        complaintId,
                        request
                )
        );
    }
}