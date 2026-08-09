package com.sankar.aicip.service.impl;


import com.sankar.aicip.dto.response.CategoryStatisticsResponse;
import com.sankar.aicip.dto.response.DashboardStatisticsResponse;
import com.sankar.aicip.dto.response.StatusStatisticsResponse;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.exception.ResourceNotFoundException;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.repository.UserRepository;
import com.sankar.aicip.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    public DashboardServiceImpl(
            ComplaintRepository complaintRepository,
            UserRepository userRepository) {

        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DashboardStatisticsResponse getDashboardStatistics() {

        DashboardStatisticsResponse response =
                new DashboardStatisticsResponse();

        response.setTotalComplaints(
                complaintRepository.count());

        response.setPendingComplaints(
                complaintRepository.countByStatus(
                        ComplaintStatus.PENDING));

        response.setUnderReviewComplaints(
                complaintRepository.countByStatus(
                        ComplaintStatus.UNDER_REVIEW));

        response.setInProgressComplaints(
                complaintRepository.countByStatus(
                        ComplaintStatus.IN_PROGRESS));

        response.setResolvedComplaints(
                complaintRepository.countByStatus(
                        ComplaintStatus.RESOLVED));

        response.setRejectedComplaints(
                complaintRepository.countByStatus(
                        ComplaintStatus.REJECTED));

        return response;
    }

    @Override
    public DashboardStatisticsResponse getMyStatistics(String email) {

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found."));

        DashboardStatisticsResponse response =
                new DashboardStatisticsResponse();

        response.setTotalComplaints(
                complaintRepository.countByCitizen(citizen));

        response.setPendingComplaints(
                complaintRepository.countByCitizenAndStatus(
                        citizen, ComplaintStatus.PENDING));

        response.setUnderReviewComplaints(
                complaintRepository.countByCitizenAndStatus(
                        citizen, ComplaintStatus.UNDER_REVIEW));

        response.setInProgressComplaints(
                complaintRepository.countByCitizenAndStatus(
                        citizen, ComplaintStatus.IN_PROGRESS));

        response.setResolvedComplaints(
                complaintRepository.countByCitizenAndStatus(
                        citizen, ComplaintStatus.RESOLVED));

        response.setRejectedComplaints(
                complaintRepository.countByCitizenAndStatus(
                        citizen, ComplaintStatus.REJECTED));

        return response;
    }

    @Override
    public List<CategoryStatisticsResponse> getCategoryStatistics() {

        return complaintRepository.getComplaintCountByCategory();

    }

    @Override
    public List<StatusStatisticsResponse> getStatusStatistics() {

        return complaintRepository.getComplaintCountByStatus();

    }
}
