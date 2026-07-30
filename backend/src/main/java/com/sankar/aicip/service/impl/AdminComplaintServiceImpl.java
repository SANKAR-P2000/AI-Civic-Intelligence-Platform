package com.sankar.aicip.service.impl;

import com.sankar.aicip.dto.response.admin.AdminComplaintResponse;
import com.sankar.aicip.dto.request.ComplaintStatusUpdateRequest;
import com.sankar.aicip.entity.Complaint;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.exception.ResourceNotFoundException;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.service.AdminComplaintService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminComplaintServiceImpl
        implements AdminComplaintService {

    private final ComplaintRepository complaintRepository;

    public AdminComplaintServiceImpl(
            ComplaintRepository complaintRepository) {

        this.complaintRepository = complaintRepository;
    }

    private AdminComplaintResponse mapToResponse(
            Complaint complaint) {

        return AdminComplaintResponse.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .category(complaint.getCategory())
                .status(complaint.getStatus())
                .location(complaint.getLocation())
                .imageUrl(complaint.getImageUrl())
                .citizenName(
                        complaint.getCitizen().getFullName())
                .citizenEmail(
                        complaint.getCitizen().getEmail())
                .createdAt(complaint.getCreatedAt())
                .updatedAt(complaint.getUpdatedAt())
                .build();
    }

    @Override
    public List<AdminComplaintResponse> getAllComplaints() {

        return complaintRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AdminComplaintResponse getComplaintById(
            Long complaintId) {

        Complaint complaint = complaintRepository
                .findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Complaint not found."));

        return mapToResponse(complaint);
    }

    @Override
    public List<AdminComplaintResponse> getComplaintsByStatus(
            ComplaintStatus status) {

        return complaintRepository
                .findByStatusOrderByCreatedAtDesc(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AdminComplaintResponse> searchComplaints(
            String keyword) {

        return complaintRepository
                .searchComplaints(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public AdminComplaintResponse updateComplaintStatus(
            Long complaintId,
            ComplaintStatusUpdateRequest request) {

        Complaint complaint = complaintRepository
                .findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found."));

        complaint.setStatus(request.getStatus());

        Complaint updatedComplaint =
                complaintRepository.save(complaint);

        return mapToResponse(updatedComplaint);
    }
}