package com.sankar.aicip.dto.response.admin;

import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Admin Complaint Response",
        description = "Response containing complaint details for administrative operations."
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminComplaintResponse {
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

}