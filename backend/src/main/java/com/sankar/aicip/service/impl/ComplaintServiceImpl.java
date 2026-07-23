package com.sankar.aicip.service.impl;
import com.sankar.aicip.dto.request.CreateComplaintRequest;
import com.sankar.aicip.dto.response.ComplaintResponse;
import com.sankar.aicip.entity.Complaint;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.repository.UserRepository;
import com.sankar.aicip.service.ComplaintService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public ComplaintServiceImpl(
            ComplaintRepository complaintRepository,
            UserRepository userRepository) {

        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    // methods...
    @Override
    public ComplaintResponse createComplaint(CreateComplaintRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        Complaint complaint = new Complaint();

        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setLocation(request.getLocation());
        complaint.setImageUrl(request.getImageUrl());

        complaint.setCitizen(citizen);

        Complaint savedComplaint =
                complaintRepository.save(complaint);

        return mapToResponse(savedComplaint);
    }
    @Override
    public List<ComplaintResponse> getMyComplaints() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        return complaintRepository.findByCitizen(citizen)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public List<ComplaintResponse> getAllComplaints() {

        return complaintRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public ComplaintResponse updateComplaintStatus(
            Long complaintId,
            ComplaintStatus status) {

        Complaint complaint =
                complaintRepository.findById(complaintId)
                        .orElseThrow(() ->
                                new RuntimeException("Complaint not found."));

        complaint.setStatus(status);

        Complaint updatedComplaint =
                complaintRepository.save(complaint);

        return mapToResponse(updatedComplaint);
    }
    @Override
    public ComplaintResponse trackComplaint(Long complaintId) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() ->
                        new RuntimeException("Complaint not found."));

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
        if (complaint.getImageUrl() != null) {

            response.setImageUrl(
                    "http://localhost:8080/uploads/"
                            + complaint.getImageUrl());

        }

        response.setCitizenName(
                complaint.getCitizen().getFullName());

        response.setCitizenEmail(
                complaint.getCitizen().getEmail());

        response.setCreatedAt(
                complaint.getCreatedAt());

        response.setUpdatedAt(
                complaint.getUpdatedAt());

        return response;
    }
}
