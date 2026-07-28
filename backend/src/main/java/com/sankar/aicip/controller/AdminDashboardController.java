package com.sankar.aicip.controller;

import com.sankar.aicip.dto.response.admin.DashboardStatisticsResponse;
import com.sankar.aicip.service.AdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    public AdminDashboardController(
            AdminDashboardService adminDashboardService) {

        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DashboardStatisticsResponse> getDashboardStatistics() {

        return ResponseEntity.ok(
                adminDashboardService.getDashboardStatistics()
        );
    }
}