package com.sankar.aicip.service.impl;

import com.sankar.aicip.dto.response.admin.DashboardStatisticsResponse;
import com.sankar.aicip.enums.ComplaintStatus;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.service.AdminDashboardService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {
    private static final Logger logger =
            LoggerFactory.getLogger(AdminDashboardServiceImpl.class);

    private final ComplaintRepository complaintRepository;

    public AdminDashboardServiceImpl(
            ComplaintRepository complaintRepository) {

        this.complaintRepository = complaintRepository;
    }

    @Override
    public DashboardStatisticsResponse getDashboardStatistics() {

        logger.info("Generating dashboard statistics.");

        DashboardStatisticsResponse response =
                DashboardStatisticsResponse.builder()

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

        logger.info("Dashboard statistics generated successfully.");

        return response;
    }

}