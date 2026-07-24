# Complaint Submission Email

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 17 – Notifications & Email Module

---

# Objective

Automatically send a confirmation email after a complaint is successfully created.

---

# Workflow

Citizen

↓

Create Complaint

↓

Complaint Saved

↓

Email Service

↓

Gmail SMTP

↓

Citizen Email Inbox

---

# Trigger

A new complaint is created.

---

# Email Subject

Complaint Submitted Successfully

---

# Email Content

- Citizen Name
- Complaint ID
- Complaint Category
- Complaint Status

---

# Benefits

- Immediate confirmation to the citizen.
- Improves user experience.
- Demonstrates event-driven backend processing.
- Uses real Gmail SMTP.

---

# Technologies

- Spring Boot Mail
- JavaMailSender
- Gmail SMTP

---

# Status

Phase 17.5

Completed
