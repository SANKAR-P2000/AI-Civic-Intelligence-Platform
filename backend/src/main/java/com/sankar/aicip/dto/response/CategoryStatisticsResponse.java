package com.sankar.aicip.dto.response;

import com.sankar.aicip.enums.ComplaintCategory;

public class CategoryStatisticsResponse {


    private ComplaintCategory category;
    private long count;

    public CategoryStatisticsResponse() {
    }

    public CategoryStatisticsResponse(
            ComplaintCategory category,
            long count) {

        this.category = category;
        this.count = count;
    }

    public ComplaintCategory getCategory() {
        return category;
    }

    public void setCategory(ComplaintCategory category) {
        this.category = category;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}