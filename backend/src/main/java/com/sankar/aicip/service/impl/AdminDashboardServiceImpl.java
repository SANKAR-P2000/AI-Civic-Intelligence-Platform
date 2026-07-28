package com.sankar.aicip.service.impl;

import com.sankar.aicip.dto.response.admin.DashboardStatisticsResponse;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.service.AdminDashboardService;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final ComplaintRepository complaintRepository;

    public AdminDashboardServiceImpl(
            ComplaintRepository complaintRepository) {

        this.complaintRepository = complaintRepository;
    }

    @Override
    public DashboardStatisticsResponse getDashboardStatistics() {

        return DashboardStatisticsResponse.builder()

                .totalComplaints(
                        complaintRepository.count())

                .pending(
                        complaintRepository.countByStatus(
                                ComplaintStatus.PENDING))

                .underReview(
                        complaintRepository.countByStatus(
                                ComplaintStatus.UNDER_REVIEW))

                .inProgress(
                        complaintRepository.countByStatus(
                                ComplaintStatus.IN_PROGRESS))

                .resolved(
                        complaintRepository.countByStatus(
                                ComplaintStatus.RESOLVED))

                .rejected(
                        complaintRepository.countByStatus(
                                ComplaintStatus.REJECTED))

                .build();
    }
}