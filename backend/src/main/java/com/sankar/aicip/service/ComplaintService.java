package com.sankar.aicip.service;

import com.sankar.aicip.dto.request.CreateComplaintRequest;
import com.sankar.aicip.dto.response.ComplaintResponse;
import com.sankar.aicip.enums.ComplaintStatus;

import com.sankar.aicip.dto.request.ComplaintSearchRequest;
import com.sankar.aicip.dto.response.ComplaintAnalyticsSummaryResponse;
import com.sankar.aicip.dto.response.PageResponse;

import java.util.List;

public interface ComplaintService {

    ComplaintResponse createComplaint(CreateComplaintRequest request);

    List<ComplaintResponse> getMyComplaints();

    List<ComplaintResponse> getAllComplaints();
    ComplaintResponse updateComplaintStatus(
            Long complaintId,
            ComplaintStatus status);
    ComplaintResponse trackComplaint(Long complaintId);

    PageResponse<ComplaintResponse> searchComplaints(ComplaintSearchRequest request);

    PageResponse<ComplaintResponse> searchNearbyComplaints(Double lat, Double lng, Double radiusKm, int page, int size);

    ComplaintAnalyticsSummaryResponse getAnalyticsSummary();
}