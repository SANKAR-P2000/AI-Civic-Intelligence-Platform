package com.sankar.aicip.dto.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatisticsResponse {

    private long totalComplaints;

    private long pending;

    private long underReview;

    private long inProgress;

    private long resolved;

    private long rejected;

}