package com.sankar.aicip.dto.request;

import com.sankar.aicip.enums.ComplaintCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Create Complaint Request",
        description = "Request payload used by a citizen to create a new complaint."
)
public class CreateComplaintRequest {

    @NotBlank(message = "Title is required.")
    @Size(max = 150, message = "Title must not exceed 150 characters.")
    @Schema(
            description = "Complaint title",
            example = "Street light not working",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;

    @NotBlank(message = "Description is required.")
    @Size(max = 1000, message = "Description must not exceed 1000 characters.")
    @Schema(
            description = "Detailed description of the complaint",
            example = "The street light near Gandhi Road has not been working for the last three days.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String description;

    @NotNull(message = "Category is required.")
    @Schema(
            description = "Complaint category",
            example = "ELECTRICITY",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private ComplaintCategory category;

    @NotBlank(message = "Location is required.")
    @Size(max = 255, message = "Location must not exceed 255 characters.")
    @Schema(
            description = "Location where the issue occurred",
            example = "Gandhi Road, Villupuram",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String location;

    @Schema(
            description = "Optional image URL supporting the complaint",
            example = "https://example.com/images/street-light.jpg"
    )
    private String imageUrl;

    public CreateComplaintRequest() {
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
}