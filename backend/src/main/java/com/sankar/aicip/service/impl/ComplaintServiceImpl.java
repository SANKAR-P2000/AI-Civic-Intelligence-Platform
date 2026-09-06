package com.sankar.aicip.service.impl;

import com.sankar.aicip.dto.request.CreateComplaintRequest;
import com.sankar.aicip.dto.response.ComplaintResponse;
import com.sankar.aicip.dto.response.PageResponse;
import com.sankar.aicip.dto.response.ComplaintAnalyticsSummaryResponse;
import com.sankar.aicip.dto.request.ComplaintSearchRequest;
import com.sankar.aicip.entity.Complaint;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.exception.ResourceNotFoundException;
import com.sankar.aicip.exception.BadRequestException;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.repository.UserRepository;
import com.sankar.aicip.repository.specification.ComplaintSpecification;
import com.sankar.aicip.service.ComplaintService;
import com.sankar.aicip.service.email.EmailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private static final Logger logger =
            LoggerFactory.getLogger(ComplaintServiceImpl.class);

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public ComplaintServiceImpl(
            ComplaintRepository complaintRepository,
            UserRepository userRepository,
            EmailService emailService) {

        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    // methods...
    @Override
    public ComplaintResponse createComplaint(CreateComplaintRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        logger.info("Creating complaint for user: {}", email);

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        Complaint complaint = new Complaint();

        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setLocation(request.getLocation());
        complaint.setLatitude(request.getLatitude());
        complaint.setLongitude(request.getLongitude());
        complaint.setImageUrl(request.getImageUrl());

        complaint.setCitizen(citizen);

        Complaint savedComplaint =
                complaintRepository.save(complaint);
        logger.info("Complaint created successfully. Complaint ID: {}",
                savedComplaint.getId());
        try {

            emailService.sendComplaintSubmittedEmail(
                    savedComplaint.getCitizen().getEmail(),
                    savedComplaint.getCitizen().getFullName(),
                    savedComplaint.getId(),
                    savedComplaint.getCategory().name(),
                    savedComplaint.getStatus().name()
            );

        } catch (Exception ex) {

            logger.warn(
                    "Complaint {} created, but email notification failed.",
                    savedComplaint.getId(),
                    ex
            );
        }

        return mapToResponse(savedComplaint);
    }

    @Override
    public List<ComplaintResponse> getMyComplaints() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        logger.info("Fetching complaints for user: {}", email);

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        List<ComplaintResponse> complaints =
                complaintRepository.findByCitizen(citizen)
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());

        logger.info("Returned {} complaints.",
                complaints.size());

        return complaints;
    }

    @Override
    public List<ComplaintResponse> getAllComplaints() {
        logger.info("Fetching all complaints.");

        List<ComplaintResponse> complaints =
                complaintRepository.findAll()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());

        logger.info("Returned {} complaints.",
                complaints.size());

        return complaints;
    }

    @Override
    public ComplaintResponse updateComplaintStatus(
            Long complaintId,
            ComplaintStatus status) {
        logger.info("Updating complaint {} to status {}",
                complaintId,
                status);

        Complaint complaint =
                complaintRepository.findById(complaintId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Complaint not found."));

        complaint.setStatus(status);

        Complaint updatedComplaint =
                complaintRepository.save(complaint);
        logger.info("Complaint {} updated successfully.",
                complaintId);
        try {

            emailService.sendComplaintStatusUpdatedEmail(
                    updatedComplaint.getCitizen().getEmail(),
                    updatedComplaint.getCitizen().getFullName(),
                    updatedComplaint.getId(),
                    updatedComplaint.getStatus().name()
            );

        } catch (Exception ex) {

            logger.warn(
                    "Complaint {} updated, but email notification failed.",
                    complaintId,
                    ex
            );
        }

        return mapToResponse(updatedComplaint);
    }

    @Override
    public ComplaintResponse trackComplaint(Long complaintId) {
        logger.info("Tracking complaint {}",
                complaintId);
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint not found."));
        logger.info("Complaint {} returned successfully.",
                complaintId);
        return mapToResponse(complaint);
    }

    private ComplaintResponse mapToResponse(Complaint complaint) {

        ComplaintResponse response = new ComplaintResponse();

        response.setId(complaint.getId());
        response.setTitle(complaint.getTitle());
        response.setDescription(complaint.getDescription());
        response.setCategory(complaint.getCategory());
        response.setStatus(complaint.getStatus());
        response.setLocation(complaint.getLocation());
        response.setLatitude(complaint.getLatitude());
        response.setLongitude(complaint.getLongitude());
        if (complaint.getImageUrl() != null) {
            response.setImageUrl("/uploads/" + complaint.getImageUrl());
        }

        response.setCitizenName(complaint.getCitizen().getFullName());
        response.setCitizenEmail(complaint.getCitizen().getEmail());
        response.setCreatedAt(complaint.getCreatedAt());
        response.setUpdatedAt(complaint.getUpdatedAt());

        return response;
    }

    @Override
    public PageResponse<ComplaintResponse> searchComplaints(ComplaintSearchRequest request) {
        logger.info("Executing searchComplaints with keyword: '{}', category: {}, status: {}, page: {}",
                request.getKeyword(), request.getCategory(), request.getStatus(), request.getPage());

        if (request.getFromDate() != null && request.getToDate() != null && request.getFromDate().isAfter(request.getToDate())) {
            throw new BadRequestException("From date cannot be after to date.");
        }

        Long citizenId = null;
        if (Boolean.TRUE.equals(request.getCitizenOnly())) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
                User citizen = userRepository.findByEmail(authentication.getName()).orElse(null);
                if (citizen != null) {
                    citizenId = citizen.getId();
                }
            }
        }

        Sort.Direction direction = "asc".equalsIgnoreCase(request.getSortDirection()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(direction, request.getSortBy()));

        Specification<Complaint> spec = ComplaintSpecification.filterComplaints(
                request.getKeyword(),
                request.getCategory(),
                request.getStatus(),
                request.getCity(),
                request.getFromDate(),
                request.getToDate(),
                citizenId
        );

        Page<Complaint> pageResult = complaintRepository.findAll(spec, pageable);

        List<ComplaintResponse> content = pageResult.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.isLast(),
                pageResult.isFirst()
        );
    }

    @Override
    public PageResponse<ComplaintResponse> searchNearbyComplaints(Double lat, Double lng, Double radiusKm, int page, int size) {
        logger.info("Executing searchNearbyComplaints for lat: {}, lng: {}, radiusKm: {}", lat, lng, radiusKm);

        if (lat == null || lat < -90 || lat > 90) {
            throw new BadRequestException("Latitude must be between -90 and 90.");
        }
        if (lng == null || lng < -180 || lng > 180) {
            throw new BadRequestException("Longitude must be between -180 and 180.");
        }
        if (radiusKm == null || radiusKm <= 0) {
            throw new BadRequestException("Radius in km must be positive.");
        }

        int validatedPage = Math.max(0, page);
        int validatedSize = (size <= 0) ? 10 : Math.min(size, 100);

        Pageable pageable = PageRequest.of(validatedPage, validatedSize);

        Page<Complaint> pageResult = complaintRepository.findNearbyComplaints(lat, lng, radiusKm, pageable);

        List<ComplaintResponse> content = pageResult.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.isLast(),
                pageResult.isFirst()
        );
    }

    @Override
    public ComplaintAnalyticsSummaryResponse getAnalyticsSummary() {
        logger.info("Generating complaint analytics summary.");
        ComplaintAnalyticsSummaryResponse summary = new ComplaintAnalyticsSummaryResponse();

        long total = complaintRepository.count();
        long pending = complaintRepository.countByStatus(ComplaintStatus.PENDING);
        long inProgress = complaintRepository.countByStatus(ComplaintStatus.IN_PROGRESS);
        long resolved = complaintRepository.countByStatus(ComplaintStatus.RESOLVED);
        long rejected = complaintRepository.countByStatus(ComplaintStatus.REJECTED);

        summary.setTotalComplaints(total);
        summary.setPendingComplaints(pending);
        summary.setInProgressComplaints(inProgress);
        summary.setResolvedComplaints(resolved);
        summary.setRejectedComplaints(rejected);

        // Status Breakdown
        java.util.Map<ComplaintStatus, Long> statusMap = new java.util.HashMap<>();
        for (Object[] row : complaintRepository.getStatusAnalytics()) {
            if (row[0] instanceof ComplaintStatus st) {
                statusMap.put(st, (Long) row[1]);
            }
        }
        summary.setStatusBreakdown(statusMap);

        // Category Breakdown
        java.util.Map<ComplaintCategory, Long> categoryMap = new java.util.HashMap<>();
        for (Object[] row : complaintRepository.getCategoryAnalytics()) {
            if (row[0] instanceof ComplaintCategory cat) {
                categoryMap.put(cat, (Long) row[1]);
            }
        }
        summary.setCategoryBreakdown(categoryMap);

        // Location Breakdown
        java.util.Map<String, Long> locationMap = new java.util.HashMap<>();
        for (Object[] row : complaintRepository.getLocationAnalytics()) {
            if (row[0] != null) {
                locationMap.put(row[0].toString(), (Long) row[1]);
            }
        }
        summary.setLocationBreakdown(locationMap);

        // Date Trends
        java.util.Map<String, Long> dateMap = new java.util.LinkedHashMap<>();
        for (Object[] row : complaintRepository.getDateAnalytics()) {
            if (row[0] != null) {
                dateMap.put(row[0].toString(), (Long) row[1]);
            }
        }
        summary.setDateTrends(dateMap);

        return summary;
    }
}
