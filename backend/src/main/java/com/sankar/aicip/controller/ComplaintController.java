package com.sankar.aicip.controller;

import com.sankar.aicip.dto.request.CreateComplaintRequest;
import com.sankar.aicip.dto.response.ComplaintResponse;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CITIZEN')")
    public ComplaintResponse createComplaint(
            @Valid @RequestBody CreateComplaintRequest request) {

        return complaintService.createComplaint(request);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CITIZEN')")
    public List<ComplaintResponse> getMyComplaints() {

        return complaintService.getMyComplaints();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
    public List<ComplaintResponse> getAllComplaints() {

        return complaintService.getAllComplaints();
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
    public ComplaintResponse updateComplaintStatus(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status) {

        return complaintService.updateComplaintStatus(id, status);
    }
}