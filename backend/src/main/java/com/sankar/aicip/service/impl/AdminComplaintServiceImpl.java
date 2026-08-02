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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class AdminComplaintServiceImpl
        implements AdminComplaintService {
    private static final Logger logger =
            LoggerFactory.getLogger(AdminComplaintServiceImpl.class);

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

        logger.info("Fetching all complaints for admin.");

        List<AdminComplaintResponse> complaints =
                complaintRepository
                        .findAllByOrderByCreatedAtDesc()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        logger.info("Returned {} complaints.",
                complaints.size());

        return complaints;
    }

    @Override
    public AdminComplaintResponse getComplaintById(Long complaintId) {

        logger.info("Fetching complaint {}",
                complaintId);

        Complaint complaint = complaintRepository
                .findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found."));

        logger.info("Complaint {} returned successfully.",
                complaintId);

        return mapToResponse(complaint);
    }

    @Override
    public List<AdminComplaintResponse> getComplaintsByStatus(
            ComplaintStatus status) {

        logger.info("Fetching complaints with status {}",
                status);

        List<AdminComplaintResponse> complaints =
                complaintRepository
                        .findByStatusOrderByCreatedAtDesc(status)
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        logger.info("Returned {} complaints.",
                complaints.size());

        return complaints;
    }

    @Override
    public List<AdminComplaintResponse> searchComplaints(
            String keyword) {

        logger.info("Searching complaints using keyword '{}'",
                keyword);

        List<AdminComplaintResponse> complaints =
                complaintRepository
                        .searchComplaints(keyword)
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        logger.info("Search returned {} complaints.",
                complaints.size());

        return complaints;
    }

    @Override
    @Transactional
    public AdminComplaintResponse updateComplaintStatus(
            Long complaintId,
            ComplaintStatusUpdateRequest request) {

        logger.info("Updating complaint {} to status {}",
                complaintId,
                request.getStatus());

        Complaint complaint = complaintRepository
                .findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found."));

        complaint.setStatus(request.getStatus());

        Complaint updatedComplaint =
                complaintRepository.save(complaint);

        logger.info("Complaint {} updated successfully.",
                complaintId);

        return mapToResponse(updatedComplaint);
    }

}