# Dashboard Statistics Response DTO

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Create a DTO to return aggregated complaint statistics for the dashboard.

---

# Class

DashboardStatisticsResponse

---

# Fields

| Field                 | Type |
| --------------------- | ---- |
| totalComplaints       | long |
| pendingComplaints     | long |
| underReviewComplaints | long |
| inProgressComplaints  | long |
| resolvedComplaints    | long |
| rejectedComplaints    | long |

---

# Example Response

```json
{
  "totalComplaints": 120,
  "pendingComplaints": 20,
  "underReviewComplaints": 10,
  "inProgressComplaints": 15,
  "resolvedComplaints": 70,
  "rejectedComplaints": 5
}
```

---

# Purpose

This DTO provides a summarized view of complaint statistics, making it suitable for dashboard widgets and charts without exposing individual complaint records.

---

# Status

Phase 15.2

Completed
