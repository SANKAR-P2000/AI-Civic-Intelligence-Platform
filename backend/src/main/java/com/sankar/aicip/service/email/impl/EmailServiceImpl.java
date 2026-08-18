package com.sankar.aicip.service.email.impl;

import com.sankar.aicip.service.email.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender mailSender;

    private static final String APP_NAME =
            "AI Civic Intelligence Platform";

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

        try {

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);

            helper.setSubject("Complaint Submitted Successfully");
            String body = """
                    <p>Hello <b>%s</b>,</p>
                    
                    <p>
                        Your complaint has been submitted successfully.
                    </p>
                    
                    <table style="width:100%%;border-collapse:collapse;">
                    
                        <tr>
                            <td><b>Complaint ID</b></td>
                            <td>%d</td>
                        </tr>
                    
                        <tr>
                            <td><b>Category</b></td>
                            <td>%s</td>
                        </tr>
                    
                        <tr>
                            <td><b>Status</b></td>
                            <td style="color:#F9A825;">
                                <b>%s</b>
                            </td>
                        </tr>
                    
                    </table>
                    
                    <br>
                    
                    <p>
                        Thank you for helping improve your city.
                    </p>
                    """.formatted(
                    citizenName,
                    complaintId,
                    category,
                    status
            );

            String html = buildEmailTemplate(
                    APP_NAME,
                    body
            );

            helper.setText(html, true);

            mailSender.send(message);

        } catch (MessagingException e) {

            System.err.println("==================================");
            System.err.println("EMAIL SEND FAILED");
            System.err.println("Recipient : " + toEmail);
            System.err.println("Reason    : " + e.getMessage());
            System.err.println("==================================");

            e.printStackTrace();
        }
    }

    @Override
    public void sendComplaintStatusUpdatedEmail(
            String toEmail,
            String citizenName,
            Long complaintId,
            String status) {

        try {

            MimeMessage message =
                    mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);

            helper.setSubject("Complaint Status Updated");

            String statusColor = switch (status) {
                case "PENDING" -> "#F9A825";
                case "UNDER_REVIEW" -> "#FB8C00";
                case "IN_PROGRESS" -> "#1565C0";
                case "RESOLVED" -> "#2E7D32";
                case "REJECTED" -> "#C62828";
                default -> "#616161";
            };
            String body = """
                    <p>Hello <b>%s</b>,</p>
                    
                    <p>
                        The status of your complaint has been updated.
                    </p>
                    
                    <table style="width:100%%;border-collapse:collapse;">
                    
                        <tr>
                            <td style="padding:8px;"><b>Complaint ID</b></td>
                            <td style="padding:8px;">%d</td>
                        </tr>
                    
                        <tr>
                            <td style="padding:8px;"><b>New Status</b></td>
                            <td style="padding:8px;">
                    
                                <span style="
                                    background:%s;
                                    color:white;
                                    padding:6px 12px;
                                    border-radius:6px;
                                    font-weight:bold;">
                    
                                    %s
                    
                                </span>
                    
                            </td>
                        </tr>
                    
                    </table>
                    
                    <br>
                    
                    <p>
                        Thank you for using AI Civic Intelligence Platform.
                    </p>
                    """.formatted(
                    citizenName,
                    complaintId,
                    statusColor,
                    status
            );

            String html = buildEmailTemplate(
                    APP_NAME,
                    body
            );


            helper.setText(html, true);

            mailSender.send(message);

        } catch (MessagingException e) {

            System.err.println("==================================");
            System.err.println("EMAIL SEND FAILED");
            System.err.println("Recipient : " + toEmail);
            System.err.println("Reason    : " + e.getMessage());
            System.err.println("==================================");

            e.printStackTrace();

        }
    }

    @Override
    public void sendOtpEmail(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Your OTP Verification Code");
            String body = """
                    <p>Hello,</p>
                    <p>Your OTP verification code for resetting your password is: <b>%s</b></p>
                    <p>This code will expire in 10 minutes.</p>
                    <br>
                    <p>If you did not request this code, please ignore this email.</p>
                    """.formatted(otp);
            String html = buildEmailTemplate(APP_NAME, body);
            helper.setText(html, true);
            mailSender.send(message);

            System.out.println("**************************************************");
            System.out.println("[DEVELOPMENT ONLY] OTP sent via email to " + toEmail + ": " + otp);
            System.out.println("**************************************************");
            logger.info("[DEVELOPMENT ONLY] OTP sent via email to {}: {}", toEmail, otp);
        } catch (Exception e) {
            System.out.println("**************************************************");
            System.out.println("[DEVELOPMENT ONLY] SMTP delivery failed. Console fallback OTP for " + toEmail + ": " + otp);
            System.out.println("**************************************************");
            logger.info("[DEVELOPMENT ONLY] SMTP delivery failed. Console fallback OTP for {}: {}", toEmail, otp);
            System.err.println("Email delivery failed: " + e.getMessage());
        }
    }

    private String buildEmailTemplate(
            String title,
            String body) {

        return """
                <html>
                <body style="font-family:Arial;background:#f4f6f8;padding:30px;">
                
                    <div style="
                        max-width:600px;
                        margin:auto;
                        background:white;
                        border-radius:10px;
                        padding:30px;
                        box-shadow:0 0 10px #dddddd;">
                
                        <h2 style="color:#1565C0;">
                            %s
                        </h2>
                
                        %s
                
                        <hr>
                
                        <p style="
                            color:gray;
                            font-size:12px;
                            text-align:center;">
                
                            © 2026 AI Civic Intelligence Platform
                
                        </p>
                
                    </div>
                
                </body>
                </html>
                """.formatted(title, body);
    }
}



