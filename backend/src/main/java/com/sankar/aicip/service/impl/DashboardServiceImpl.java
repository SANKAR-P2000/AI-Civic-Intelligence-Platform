package com.sankar.aicip.service.impl;


import com.sankar.aicip.dto.response.DashboardStatisticsResponse;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.service.DashboardService;
import com.sankar.aicip.dto.response.CategoryStatisticsResponse;
import com.sankar.aicip.dto.response.StatusStatisticsResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ComplaintRepository complaintRepository;

    public DashboardServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
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
    public List<CategoryStatisticsResponse> getCategoryStatistics() {

        return complaintRepository.getComplaintCountByCategory();

    }

    @Override
    public List<StatusStatisticsResponse> getStatusStatistics() {

        return complaintRepository.getComplaintCountByStatus();

    }
}