package com.sankar.aicip.service.email;

public interface EmailService {

    void sendComplaintSubmittedEmail(
            String toEmail,
            String citizenName,
            Long complaintId,
            String category,
            String status
    );
    void sendComplaintStatusUpdatedEmail(
            String toEmail,
            String citizenName,
            Long complaintId,
            String status
    );

}