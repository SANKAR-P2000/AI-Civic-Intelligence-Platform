# Phase 20.1 – Admin Complaint Management Design

## Objective

Design the complaint management module for administrators.

---

# Module Purpose

Allow administrators to:

- View all complaints
- Search complaints
- Filter complaints
- View complaint details
- Update complaint status

---

# Workflow

Citizen

↓

Submit Complaint

↓

Complaint Database

↓

Administrator

↓

View Complaint

↓

Review Complaint

↓

Update Status

↓

Citizen Notification

---

# APIs Planned

GET /api/admin/complaints

GET /api/admin/complaints/{id}

GET /api/admin/complaints/search

GET /api/admin/complaints/status/{status}

PUT /api/admin/complaints/{id}/status

---

# Security

Role Required

ADMIN

---

# Complaint Status Flow

PENDING

↓

UNDER_REVIEW

↓

IN_PROGRESS

↓

RESOLVED

or

↓

REJECTED

---

# Benefits

- Centralized complaint management
- Faster issue resolution
- Transparent workflow
- Role-based access
- Easy frontend integration

---

# Status

Completed
