package com.sankar.aicip.dto.response;

import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(
        name = "Complaint Analytics Summary Response",
        description = "Aggregate complaint statistics dynamically calculated from MySQL."
)
public class ComplaintAnalyticsSummaryResponse {

    @Schema(description = "Total complaints in system", example = "150")
    private long totalComplaints;

    @Schema(description = "Pending complaints count", example = "45")
    private long pendingComplaints;

    @Schema(description = "In-progress complaints count", example = "30")
    private long inProgressComplaints;

    @Schema(description = "Resolved complaints count", example = "65")
    private long resolvedComplaints;

    @Schema(description = "Rejected complaints count", example = "10")
    private long rejectedComplaints;

    @Schema(description = "Breakdown of complaint count by status")
    private Map<ComplaintStatus, Long> statusBreakdown;

    @Schema(description = "Breakdown of complaint count by category")
    private Map<ComplaintCategory, Long> categoryBreakdown;

    @Schema(description = "Location complaint counts")
    private Map<String, Long> locationBreakdown;

    @Schema(description = "Daily complaint submission trends (YYYY-MM-DD -> Count)")
    private Map<String, Long> dateTrends;

    public ComplaintAnalyticsSummaryResponse() {
    }

    public long getTotalComplaints() {
        return totalComplaints;
    }

    public void setTotalComplaints(long totalComplaints) {
        this.totalComplaints = totalComplaints;
    }

    public long getPendingComplaints() {
        return pendingComplaints;
    }

    public void setPendingComplaints(long pendingComplaints) {
        this.pendingComplaints = pendingComplaints;
    }

    public long getInProgressComplaints() {
        return inProgressComplaints;
    }

    public void setInProgressComplaints(long inProgressComplaints) {
        this.inProgressComplaints = inProgressComplaints;
    }

    public long getResolvedComplaints() {
        return resolvedComplaints;
    }

    public void setResolvedComplaints(long resolvedComplaints) {
        this.resolvedComplaints = resolvedComplaints;
    }

    public long getRejectedComplaints() {
        return rejectedComplaints;
    }

    public void setRejectedComplaints(long rejectedComplaints) {
        this.rejectedComplaints = rejectedComplaints;
    }

    public Map<ComplaintStatus, Long> getStatusBreakdown() {
        return statusBreakdown;
    }

    public void setStatusBreakdown(Map<ComplaintStatus, Long> statusBreakdown) {
        this.statusBreakdown = statusBreakdown;
    }

    public Map<ComplaintCategory, Long> getCategoryBreakdown() {
        return categoryBreakdown;
    }

    public void setCategoryBreakdown(Map<ComplaintCategory, Long> categoryBreakdown) {
        this.categoryBreakdown = categoryBreakdown;
    }

    public Map<String, Long> getLocationBreakdown() {
        return locationBreakdown;
    }

    public void setLocationBreakdown(Map<String, Long> locationBreakdown) {
        this.locationBreakdown = locationBreakdown;
    }

    public Map<String, Long> getDateTrends() {
        return dateTrends;
    }

    public void setDateTrends(Map<String, Long> dateTrends) {
        this.dateTrends = dateTrends;
    }
}
