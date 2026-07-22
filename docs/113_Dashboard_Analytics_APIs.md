# Dashboard Analytics APIs

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 15 – Dashboard Analytics APIs

---

# Objective

Provide analytics endpoints for the dashboard so administrators and government officials can view complaint summaries and distributions.

---

# Authentication

JWT Bearer Token Required

---

# Authorized Roles

- ADMIN
- GOVERNMENT

---

# APIs

## 1. Dashboard Statistics

GET /api/dashboard/stats

Returns:

- Total Complaints
- Pending Complaints
- Under Review Complaints
- In Progress Complaints
- Resolved Complaints
- Rejected Complaints

Example:

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

## 2. Category Analytics

GET /api/dashboard/categories

Returns complaint count grouped by category.

Example:

```json
[
  {
    "category": "ROAD_DAMAGE",
    "count": 1
  }
]
```

---

## 3. Status Analytics

GET /api/dashboard/status

Returns complaint count grouped by status.

Example:

```json
[
  {
    "status": "RESOLVED",
    "count": 1
  }
]
```

---

# Technology Used

- Spring Boot
- Spring Security
- Spring Data JPA
- JPQL Constructor Expressions
- MySQL
- JWT Authentication

---

# Benefits

- Lightweight responses
- Fast aggregation using database queries
- Suitable for dashboard charts
- Clean separation between entities and API responses

---

# Status

Phase 15.9

Completed
