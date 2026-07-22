# Dashboard Analytics DTOs

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 15 – Public Complaint Tracking & Dashboard

---

# Objective

Design Data Transfer Objects (DTOs) for Dashboard Analytics APIs.

These DTOs provide summarized complaint information that can be directly consumed by the frontend to display charts, graphs, and analytics dashboards.

---

# Why Analytics DTOs?

Instead of returning complete complaint records, the dashboard only requires aggregated information such as:

- Complaint count by Category
- Complaint count by Status

Returning only the required data:

- Improves performance
- Reduces network traffic
- Simplifies frontend development
- Supports chart libraries

---

# 1. CategoryStatisticsResponse

## Purpose

Represents the total number of complaints for each complaint category.

Example categories include:

- ROAD_DAMAGE
- WATER_SUPPLY
- ELECTRICITY
- GARBAGE
- STREET_LIGHT
- DRAINAGE
- OTHER

---

## Java Class

```java
public class CategoryStatisticsResponse {

    private String category;

    private long count;

    public CategoryStatisticsResponse() {
    }

    public CategoryStatisticsResponse(String category, long count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
```

---

## Sample JSON

```json
[
  {
    "category": "ROAD_DAMAGE",
    "count": 15
  },
  {
    "category": "GARBAGE",
    "count": 8
  },
  {
    "category": "WATER_SUPPLY",
    "count": 6
  }
]
```

---

# 2. StatusStatisticsResponse

## Purpose

Represents the number of complaints grouped by complaint status.

Possible statuses:

- PENDING
- UNDER_REVIEW
- IN_PROGRESS
- RESOLVED
- REJECTED

---

## Java Class

```java
public class StatusStatisticsResponse {

    private String status;

    private long count;

    public StatusStatisticsResponse() {
    }

    public StatusStatisticsResponse(String status, long count) {
        this.status = status;
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
```

---

## Sample JSON

```json
[
  {
    "status": "PENDING",
    "count": 5
  },
  {
    "status": "UNDER_REVIEW",
    "count": 3
  },
  {
    "status": "IN_PROGRESS",
    "count": 7
  },
  {
    "status": "RESOLVED",
    "count": 20
  },
  {
    "status": "REJECTED",
    "count": 2
  }
]
```

---

# Dashboard Architecture

```
Complaint Table
        │
        ▼
ComplaintRepository
        │
        ▼
DashboardService
        │
        ▼
Analytics DTOs
        │
        ▼
DashboardController
        │
        ▼
REST API
        │
        ▼
Frontend Dashboard
```

---

# Future APIs

These DTOs will be returned by the following endpoints:

## Category Analytics

```
GET /api/dashboard/categories
```

Returns:

```json
[
  {
    "category": "ROAD_DAMAGE",
    "count": 15
  }
]
```

---

## Status Analytics

```
GET /api/dashboard/status
```

Returns:

```json
[
  {
    "status": "RESOLVED",
    "count": 25
  }
]
```

---

# Frontend Usage

These DTOs can be used to build:

- Pie Charts
- Bar Charts
- Doughnut Charts
- Line Charts
- Dashboard KPI Widgets
- Summary Cards

---

# Advantages

- Lightweight response payload
- Faster API execution
- Easy frontend integration
- Clean separation between entities and API responses
- Better scalability for analytics

---

# Learning Outcomes

After completing this phase, you understand:

- Why analytics APIs use DTOs instead of entities.
- How aggregated data is represented.
- How DTOs simplify frontend chart rendering.
- How Spring Boot services return summarized data for dashboards.

---

# Status

**Phase 15.9 (Part 1)**

**Completed**
