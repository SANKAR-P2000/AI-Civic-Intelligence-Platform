# Complaint Email Workflow

## Phase

Phase 17 – Email Notification System

---

# Objective

Verify the complete complaint notification workflow from complaint submission to status update.

---

# Workflow

Citizen Login

↓

JWT Token Generated

↓

Citizen Creates Complaint

↓

Complaint Saved to Database

↓

Complaint Submission Email Sent

↓

Admin Login

↓

Admin JWT Generated

↓

Admin Updates Complaint Status

↓

Complaint Status Updated

↓

Status Update Email Sent

---

# Complaint Submission Email

### Trigger

POST /api/complaints

### Recipient

Citizen Email Address

### Subject

Complaint Submitted Successfully

### Email Includes

- Citizen Name
- Complaint ID
- Category
- Status
- Thank You Message

---

# Complaint Status Update Email

### Trigger

PUT /api/complaints/{id}/status

### Recipient

Citizen Email Address

### Subject

Complaint Status Updated

### Email Includes

- Citizen Name
- Complaint ID
- Updated Status
- Thank You Message

---

# Tested Successfully

✓ Citizen Login

✓ Complaint Creation

✓ Complaint Submission Email

✓ Admin Login

✓ Complaint Status Update

✓ Complaint Status Email

---

# Notes

Emails may initially appear in the Spam folder because the application uses Gmail SMTP from a local development environment.

This is expected behavior.

Once marked as "Not Spam" or when using a properly configured production mail domain (SPF, DKIM, DMARC), future emails are less likely to be filtered.
