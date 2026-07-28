# Phase 19.2 – Dashboard Statistics Response DTO

## Objective

The Dashboard Statistics Response DTO transfers complaint statistics from the backend service layer to the Admin Dashboard API.

---

# DTO Name

DashboardStatisticsResponse

---

# Package

com.sankar.aicip.dto.response.admin

---

# Purpose

This DTO provides summary statistics for the administrator dashboard.

---

# Fields

| Field           | Type | Description                         |
| --------------- | ---- | ----------------------------------- |
| totalComplaints | long | Total complaints in the system      |
| pending         | long | Complaints with PENDING status      |
| underReview     | long | Complaints with UNDER_REVIEW status |
| inProgress      | long | Complaints with IN_PROGRESS status  |
| resolved        | long | Complaints with RESOLVED status     |
| rejected        | long | Complaints with REJECTED status     |

---

# Sample JSON

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

- Lightweight response
- Easy dashboard integration
- Clear separation between entity and API response
- Supports future analytics extensions

---

# Used By

GET /api/admin/dashboard
