# Dashboard Controller

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Expose dashboard statistics through REST APIs.

---

# Endpoint

GET /api/dashboard/stats

---

# Access

- ADMIN
- GOVERNMENT

---

# Authentication

JWT Token Required

Authorization

Bearer <JWT_TOKEN>

---

# Business Flow

DashboardController

↓

DashboardService

↓

DashboardServiceImpl

↓

ComplaintRepository

↓

DashboardStatisticsResponse

---

# Response

Returns:

- Total Complaints
- Pending Complaints
- Under Review Complaints
- In Progress Complaints
- Resolved Complaints
- Rejected Complaints

---

# Security

Protected using:

@PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")

---

# Status

Phase 15.6

Completed
