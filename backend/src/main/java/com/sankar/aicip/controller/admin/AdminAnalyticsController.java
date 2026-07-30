package com.sankar.aicip.controller.admin;

import com.sankar.aicip.dto.response.admin.AnalyticsResponse;
import com.sankar.aicip.service.AdminAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/analytics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminAnalyticsController {

    private final AdminAnalyticsService adminAnalyticsService;

    @GetMapping("/category")
    public List<AnalyticsResponse> getCategoryAnalytics() {
        return adminAnalyticsService.getCategoryAnalytics();
    }

    @GetMapping("/status")
    public List<AnalyticsResponse> getStatusAnalytics() {
        return adminAnalyticsService.getStatusAnalytics();
    }

    @GetMapping("/location")
    public List<AnalyticsResponse> getLocationAnalytics() {
        return adminAnalyticsService.getLocationAnalytics();
    }

    @GetMapping("/date")
    public List<AnalyticsResponse> getDateAnalytics() {
        return adminAnalyticsService.getDateAnalytics();
    }
}