package com.sankar.aicip.dto.response.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Analytics Response",
        description = "Represents a single analytics result containing a label and its corresponding count."
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsResponse {
    @Schema(
            description = "Analytics label",
            example = "ELECTRICITY"
    )
    private String label;
    @Schema(
            description = "Number of complaints for the specified label",
            example = "25"
    )
    private Long count;

}