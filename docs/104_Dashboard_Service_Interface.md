# Dashboard Service Interface

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Define the business operations required for the Dashboard module.

---

# Interface

DashboardService

---

# Method

```java
DashboardStatisticsResponse getDashboardStatistics();
```

---

# Purpose

Retrieve aggregated complaint statistics for the dashboard.

The returned information includes:

- Total Complaints
- Pending Complaints
- Under Review Complaints
- In Progress Complaints
- Resolved Complaints
- Rejected Complaints

---

# Benefits

- Separates controller from business logic.
- Improves maintainability.
- Supports dependency injection.
- Makes unit testing easier.

---

# Status

Phase 15.4

Completed
