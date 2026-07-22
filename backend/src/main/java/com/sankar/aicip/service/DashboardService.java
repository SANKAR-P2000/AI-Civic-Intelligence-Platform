package com.sankar.aicip.service;

import com.sankar.aicip.dto.response.DashboardStatisticsResponse;
import com.sankar.aicip.dto.response.CategoryStatisticsResponse;
import com.sankar.aicip.dto.response.StatusStatisticsResponse;

import java.util.List;

public interface DashboardService {


    DashboardStatisticsResponse getDashboardStatistics();


    List<CategoryStatisticsResponse> getCategoryStatistics();

    List<StatusStatisticsResponse> getStatusStatistics();

}