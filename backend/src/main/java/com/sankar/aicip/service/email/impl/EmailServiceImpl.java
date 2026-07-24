package com.sankar.aicip.service.email.impl;

import com.sankar.aicip.service.email.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendComplaintSubmittedEmail(
            String toEmail,
            String citizenName,
            Long complaintId,
            String category,
            String status) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject("Complaint Submitted Successfully");

        message.setText(
                "Hello " + citizenName + ",\n\n"
                        + "Your complaint has been submitted successfully.\n\n"
                        + "Complaint ID : " + complaintId + "\n"
                        + "Category     : " + category + "\n"
                        + "Status       : " + status + "\n\n"
                        + "Thank you for helping improve your city.\n\n"
                        + "Regards,\n"
                        + "AI Civic Intelligence Platform"
        );

        mailSender.send(message);
    }
    @Override
    public void sendComplaintStatusUpdatedEmail(
            String toEmail,
            String citizenName,
            Long complaintId,
            String status) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject("Complaint Status Updated");

        message.setText(
                "Hello " + citizenName + ",\n\n"
                        + "The status of your complaint has been updated.\n\n"
                        + "Complaint ID : " + complaintId + "\n"
                        + "New Status   : " + status + "\n\n"
                        + "Thank you for using AI Civic Intelligence Platform.\n\n"
                        + "Regards,\n"
                        + "AI Civic Intelligence Platform"
        );

        mailSender.send(message);
    }
}