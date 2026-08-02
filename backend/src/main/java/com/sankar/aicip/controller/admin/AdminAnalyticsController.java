package com.sankar.aicip.controller.admin;

import com.sankar.aicip.dto.response.admin.AnalyticsResponse;
import com.sankar.aicip.service.AdminAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/admin/analytics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminAnalyticsController {

    private static final Logger logger =
            LoggerFactory.getLogger(AdminAnalyticsController.class);
    private final AdminAnalyticsService adminAnalyticsService;

    @GetMapping("/category")
    public List<AnalyticsResponse> getCategoryAnalytics() {

        logger.info("Admin requested category analytics.");

        List<AnalyticsResponse> response =
                adminAnalyticsService.getCategoryAnalytics();

        logger.info("Category analytics returned successfully.");

        return response;
    }

    @GetMapping("/status")
    public List<AnalyticsResponse> getStatusAnalytics() {

        logger.info("Admin requested status analytics.");

        List<AnalyticsResponse> response =
                adminAnalyticsService.getStatusAnalytics();

        logger.info("Status analytics returned successfully.");

        return response;
    }

    @GetMapping("/location")
    public List<AnalyticsResponse> getLocationAnalytics() {

        logger.info("Admin requested location analytics.");

        List<AnalyticsResponse> response =
                adminAnalyticsService.getLocationAnalytics();

        logger.info("Location analytics returned successfully.");

        return response;
    }

    @GetMapping("/date")
    public List<AnalyticsResponse> getDateAnalytics() {

        logger.info("Admin requested date analytics.");

        List<AnalyticsResponse> response =
                adminAnalyticsService.getDateAnalytics();

        logger.info("Date analytics returned successfully.");

        return response;
    }
}
