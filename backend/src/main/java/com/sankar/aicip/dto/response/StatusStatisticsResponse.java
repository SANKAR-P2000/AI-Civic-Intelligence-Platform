package com.sankar.aicip.dto.response;

import com.sankar.aicip.enums.ComplaintStatus;

public class StatusStatisticsResponse {


    private ComplaintStatus status;
    private long count;

    public StatusStatisticsResponse() {
    }

    public StatusStatisticsResponse(
            ComplaintStatus status,
            long count) {

        this.status = status;
        this.count = count;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}