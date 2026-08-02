package com.sankar.aicip.service.impl;

import com.sankar.aicip.dto.response.admin.AnalyticsResponse;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.service.AdminAnalyticsService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminAnalyticsServiceImpl implements AdminAnalyticsService {
    private static final Logger logger =
            LoggerFactory.getLogger(AdminAnalyticsServiceImpl.class);

    private final ComplaintRepository complaintRepository;

    public AdminAnalyticsServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public List<AnalyticsResponse> getCategoryAnalytics() {

        logger.info("Generating category analytics.");

        List<AnalyticsResponse> response =
                complaintRepository.getCategoryAnalytics()
                        .stream()
                        .map(row -> new AnalyticsResponse(
                                row[0].toString(),
                                ((Number) row[1]).longValue()))
                        .collect(Collectors.toList());

        logger.info("Category analytics generated successfully.");

        return response;
    }

    @Override
    public List<AnalyticsResponse> getStatusAnalytics() {

        logger.info("Generating status analytics.");

        List<AnalyticsResponse> response =
                complaintRepository.getStatusAnalytics()
                        .stream()
                        .map(row -> new AnalyticsResponse(
                                row[0].toString(),
                                ((Number) row[1]).longValue()))
                        .collect(Collectors.toList());

        logger.info("Status analytics generated successfully.");

        return response;
    }

    @Override
    public List<AnalyticsResponse> getLocationAnalytics() {

        logger.info("Generating location analytics.");

        List<AnalyticsResponse> response =
                complaintRepository.getLocationAnalytics()
                        .stream()
                        .map(row -> new AnalyticsResponse(
                                row[0].toString(),
                                ((Number) row[1]).longValue()))
                        .collect(Collectors.toList());

        logger.info("Location analytics generated successfully.");

        return response;
    }

    @Override
    public List<AnalyticsResponse> getDateAnalytics() {

        logger.info("Generating date analytics.");

        List<AnalyticsResponse> response =
                complaintRepository.getDateAnalytics()
                        .stream()
                        .map(row -> new AnalyticsResponse(
                                row[0].toString(),
                                ((Number) row[1]).longValue()))
                        .collect(Collectors.toList());

        logger.info("Date analytics generated successfully.");

        return response;
    }
}