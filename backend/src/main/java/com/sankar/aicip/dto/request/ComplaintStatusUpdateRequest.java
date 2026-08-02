package com.sankar.aicip.dto.request;

import com.sankar.aicip.enums.ComplaintStatus;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Complaint Status Update Request",
        description = "Request payload used by administrators to update the status of a complaint."
)
public class ComplaintStatusUpdateRequest {

    @NotNull(message = "Status is required.")
    @Schema(
            description = "New complaint status",
            example = "UNDER_REVIEW",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private ComplaintStatus status;

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }
}