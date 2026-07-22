package com.sankar.aicip.service;

import com.sankar.aicip.dto.request.CreateComplaintRequest;
import com.sankar.aicip.dto.response.ComplaintResponse;
import com.sankar.aicip.enums.ComplaintStatus;

import java.util.List;

public interface ComplaintService {

    ComplaintResponse createComplaint(CreateComplaintRequest request);

    List<ComplaintResponse> getMyComplaints();

    List<ComplaintResponse> getAllComplaints();
    ComplaintResponse updateComplaintStatus(
            Long complaintId,
            ComplaintStatus status);
    ComplaintResponse trackComplaint(Long complaintId);
}