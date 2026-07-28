package com.sankar.aicip.service;

import com.sankar.aicip.dto.response.admin.AdminComplaintResponse;
import com.sankar.aicip.enums.ComplaintStatus;

import java.util.List;

public interface AdminComplaintService {

    List<AdminComplaintResponse> getAllComplaints();

    AdminComplaintResponse getComplaintById(Long complaintId);

    List<AdminComplaintResponse> getComplaintsByStatus(
            ComplaintStatus status);

    List<AdminComplaintResponse> searchComplaints(String keyword);

}