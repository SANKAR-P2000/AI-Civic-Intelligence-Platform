# Dashboard Statistics API

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Provide aggregated complaint statistics for the dashboard.

---

# Endpoint

GET /api/dashboard/stats

---

# Access

- ADMIN
- GOVERNMENT

---

# Authentication

JWT Bearer Token Required.

---

# Sample Request

GET http://localhost:8080/api/dashboard/stats

---

# Sample Response

```json
{
  "totalComplaints": 1,
  "pendingComplaints": 0,
  "underReviewComplaints": 0,
  "inProgressComplaints": 0,
  "resolvedComplaints": 1,
  "rejectedComplaints": 0
}
```

---

# Data Source

The statistics are calculated using Spring Data JPA repository methods:

- `count()`
- `countByStatus(ComplaintStatus status)`

---

# Expected HTTP Status

| Status           | Meaning                          |
| ---------------- | -------------------------------- |
| 200 OK           | Statistics returned successfully |
| 401 Unauthorized | Missing or invalid JWT           |
| 403 Forbidden    | User role is not authorized      |

---

# Security

Allowed Roles:

- ADMIN
- GOVERNMENT

Denied Role:

- CITIZEN

---

# Status

Phase 15.7

Completed
