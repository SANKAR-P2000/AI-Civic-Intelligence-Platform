# Dashboard Analytics Controller

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Expose REST APIs for dashboard analytics.

---

# Endpoints

## GET /api/dashboard/categories

Returns complaint count grouped by category.

---

## GET /api/dashboard/status

Returns complaint count grouped by complaint status.

---

# Authentication

JWT Bearer Token Required.

---

# Authorized Roles

- ADMIN
- GOVERNMENT

---

# Controller

DashboardController

---

# Service

DashboardService

---

# Response DTOs

- CategoryStatisticsResponse
- StatusStatisticsResponse

---

# Security

Protected using:

@PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")

---

# Status

Phase 15.9.4

Completed
