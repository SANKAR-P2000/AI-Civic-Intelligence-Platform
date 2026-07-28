package com.sankar.aicip.dto.response.admin;

import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminComplaintResponse {

    private Long id;

    private String title;

    private String description;

    private ComplaintCategory category;

    private ComplaintStatus status;

    private String location;

    private String imageUrl;

    private String citizenName;

    private String citizenEmail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}