# Phase 19.1 – Admin Dashboard Design

## Objective

Provide a dashboard API for administrators that returns the overall complaint statistics of the system.

---

# Dashboard API

GET /api/admin/dashboard

---

# Purpose

The Admin Dashboard provides a quick overview of complaint statistics without retrieving all complaint records.

---

# Dashboard Metrics

- Total Complaints
- Pending Complaints
- Under Review Complaints
- In Progress Complaints
- Resolved Complaints
- Rejected Complaints

---

# Sample Response

```json
{
  "totalComplaints": 245,
  "pending": 48,
  "underReview": 22,
  "inProgress": 71,
  "resolved": 98,
  "rejected": 6
}
```

---

# Benefits

- Fast dashboard loading
- KPI cards
- Executive summary
- Supports future analytics charts

---

# Security

Only ADMIN users can access this endpoint.

Unauthorized users should receive:

HTTP 403 Forbidden

---

# Next Step

Create Dashboard Statistics DTO.
