package com.sankar.aicip.dto.response;

import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Complaint Response",
        description = "Response containing complaint details."
)
public class ComplaintResponse {
    @Schema(
            description = "Unique complaint identifier",
            example = "101"
    )
    private Long id;
    @Schema(
            description = "Complaint title",
            example = "Street light not working"
    )
    private String title;
    @Schema(
            description = "Detailed complaint description",
            example = "The street light near Gandhi Road has not been working for three days."
    )
    private String description;
    @Schema(
            description = "Complaint category",
            example = "ELECTRICITY"
    )
    private ComplaintCategory category;
    @Schema(
            description = "Current complaint status",
            example = "UNDER_REVIEW"
    )
    private ComplaintStatus status;
    @Schema(
            description = "Complaint location",
            example = "Gandhi Road, Villupuram"
    )
    private String location;
    @Schema(
            description = "Complaint image URL",
            example = "https://example.com/images/street-light.jpg"
    )
    private String imageUrl;
    @Schema(
            description = "Citizen who submitted the complaint",
            example = "Sankar P"
    )
    private String citizenName;
    @Schema(
            description = "Citizen email address",
            example = "sankar@example.com"
    )
    private String citizenEmail;
    @Schema(
            description = "Complaint creation timestamp",
            example = "2026-08-02T10:30:45"
    )
    private LocalDateTime createdAt;
    @Schema(
            description = "Last complaint update timestamp",
            example = "2026-08-03T14:15:20"
    )
    private LocalDateTime updatedAt;

    public ComplaintResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getCitizenEmail() {
        return citizenEmail;
    }

    public void setCitizenEmail(String citizenEmail) {
        this.citizenEmail = citizenEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}