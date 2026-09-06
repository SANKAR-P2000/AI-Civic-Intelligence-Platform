package com.sankar.aicip.dto.request;

import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Schema(
        name = "Complaint Search Request",
        description = "Query parameters for searching and filtering complaints."
)
public class ComplaintSearchRequest {

    @Schema(description = "Keyword search for title, description, location, or tracking ID", example = "road")
    private String keyword;

    @Schema(description = "Complaint category filter", example = "ROAD_DAMAGE")
    private ComplaintCategory category;

    @Schema(description = "Complaint status filter", example = "PENDING")
    private ComplaintStatus status;

    @Schema(description = "City / location string filter", example = "Villupuram")
    private String city;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "Filter complaints created on or after fromDate (YYYY-MM-DD)", example = "2026-08-01")
    private LocalDate fromDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(description = "Filter complaints created on or before toDate (YYYY-MM-DD)", example = "2026-09-06")
    private LocalDate toDate;

    @Schema(description = "Optional filter for citizen-owned complaints only", example = "false")
    private Boolean citizenOnly = false;

    @Schema(description = "Page index (0-based)", example = "0")
    private int page = 0;

    @Schema(description = "Page size limit", example = "10")
    private int size = 10;

    @Schema(description = "Sort field (createdAt, title, status, category)", example = "createdAt")
    private String sortBy = "createdAt";

    @Schema(description = "Sort direction (asc or desc)", example = "desc")
    private String sortDirection = "desc";

    public ComplaintSearchRequest() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ComplaintCategory getCategory() {
        return category;
    }

    public void setCategory(ComplaintCategory category) {
        this.category = category;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Boolean getCitizenOnly() {
        return citizenOnly;
    }

    public void setCitizenOnly(Boolean citizenOnly) {
        this.citizenOnly = citizenOnly;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = Math.max(0, page);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = (size <= 0) ? 10 : Math.min(size, 100);
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = (sortBy == null || sortBy.isBlank()) ? "createdAt" : sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = "asc".equalsIgnoreCase(sortDirection) ? "asc" : "desc";
    }
}
