package com.sankar.aicip.controller.admin;

import com.sankar.aicip.dto.response.admin.AnalyticsResponse;
import com.sankar.aicip.service.AdminAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Admin Analytics",
        description = "Administrative analytics APIs for complaint statistics"
)
@RestController
@RequestMapping("/api/admin/analytics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminAnalyticsController {

    private static final Logger logger =
            LoggerFactory.getLogger(AdminAnalyticsController.class);
    private final AdminAnalyticsService adminAnalyticsService;

    @Operation(
            summary = "Category Analytics",
            description = "Returns complaint statistics grouped by category."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category analytics returned successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/category")
    public List<AnalyticsResponse> getCategoryAnalytics() {

        logger.info("Admin requested category analytics.");

        List<AnalyticsResponse> response =
                adminAnalyticsService.getCategoryAnalytics();

        logger.info("Category analytics returned successfully.");

        return response;
    }

    @Operation(
            summary = "Status Analytics",
            description = "Returns complaint statistics grouped by status."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status analytics returned successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/status")
    public List<AnalyticsResponse> getStatusAnalytics() {

        logger.info("Admin requested status analytics.");

        List<AnalyticsResponse> response =
                adminAnalyticsService.getStatusAnalytics();

        logger.info("Status analytics returned successfully.");

        return response;
    }

    @Operation(
            summary = "Location Analytics",
            description = "Returns complaint statistics grouped by location."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Location analytics returned successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/location")
    public List<AnalyticsResponse> getLocationAnalytics() {

        logger.info("Admin requested location analytics.");

        List<AnalyticsResponse> response =
                adminAnalyticsService.getLocationAnalytics();

        logger.info("Location analytics returned successfully.");

        return response;
    }

    @Operation(
            summary = "Date Analytics",
            description = "Returns complaint statistics grouped by complaint creation date."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Date analytics returned successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/date")
    public List<AnalyticsResponse> getDateAnalytics() {

        logger.info("Admin requested date analytics.");

        List<AnalyticsResponse> response =
                adminAnalyticsService.getDateAnalytics();

        logger.info("Date analytics returned successfully.");

        return response;
    }
}
