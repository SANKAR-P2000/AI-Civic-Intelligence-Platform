package com.sankar.aicip.service.impl;

import com.sankar.aicip.dto.response.admin.AnalyticsResponse;
import com.sankar.aicip.repository.ComplaintRepository;
import com.sankar.aicip.service.AdminAnalyticsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminAnalyticsServiceImpl implements AdminAnalyticsService {

    private final ComplaintRepository complaintRepository;

    public AdminAnalyticsServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public List<AnalyticsResponse> getCategoryAnalytics() {

        return complaintRepository.getCategoryAnalytics()
                .stream()
                .map(row -> new AnalyticsResponse(
                        row[0].toString(),
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnalyticsResponse> getStatusAnalytics() {

        return complaintRepository.getStatusAnalytics()
                .stream()
                .map(row -> new AnalyticsResponse(
                        row[0].toString(),
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnalyticsResponse> getLocationAnalytics() {

        return complaintRepository.getLocationAnalytics()
                .stream()
                .map(row -> new AnalyticsResponse(
                        row[0].toString(),
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnalyticsResponse> getDateAnalytics() {

        return complaintRepository.getDateAnalytics()
                .stream()
                .map(row -> new AnalyticsResponse(
                        row[0].toString(),
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());
    }
}