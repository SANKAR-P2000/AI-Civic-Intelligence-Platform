package com.sankar.aicip.dto.response;

public class DashboardStatisticsResponse {

    private long totalComplaints;

    private long pendingComplaints;

    private long underReviewComplaints;

    private long inProgressComplaints;

    private long resolvedComplaints;

    private long rejectedComplaints;

    public DashboardStatisticsResponse() {
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

    public long getUnderReviewComplaints() {
        return underReviewComplaints;
    }

    public void setUnderReviewComplaints(long underReviewComplaints) {
        this.underReviewComplaints = underReviewComplaints;
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
}