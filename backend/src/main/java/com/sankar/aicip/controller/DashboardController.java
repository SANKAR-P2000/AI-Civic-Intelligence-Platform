package com.sankar.aicip.controller;

import com.sankar.aicip.dto.response.CategoryStatisticsResponse;
import com.sankar.aicip.dto.response.DashboardStatisticsResponse;
import com.sankar.aicip.dto.response.StatusStatisticsResponse;
import com.sankar.aicip.service.DashboardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
    public DashboardStatisticsResponse getDashboardStatistics() {

        return dashboardService.getDashboardStatistics();
    }

    @GetMapping("/mystats")
    @PreAuthorize("hasRole('CITIZEN')")
    public DashboardStatisticsResponse getMyStatistics(
            Authentication authentication) {

        return dashboardService.getMyStatistics(
                authentication.getName());
    }

    @GetMapping("/categories")
    @PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
    public List<CategoryStatisticsResponse> getCategoryStatistics() {

        return dashboardService.getCategoryStatistics();
    }

    @GetMapping("/status")
    @PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
    public List<StatusStatisticsResponse> getStatusStatistics() {

        return dashboardService.getStatusStatistics();
    }
}
