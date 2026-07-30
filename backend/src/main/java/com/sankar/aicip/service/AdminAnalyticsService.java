package com.sankar.aicip.service;

import com.sankar.aicip.dto.response.admin.AnalyticsResponse;

import java.util.List;

public interface AdminAnalyticsService {

    List<AnalyticsResponse> getCategoryAnalytics();

    List<AnalyticsResponse> getStatusAnalytics();

    List<AnalyticsResponse> getLocationAnalytics();

    List<AnalyticsResponse> getDateAnalytics();

}