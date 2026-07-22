# Dashboard Module Design

## Objective

Design the Public Complaint Tracking and Dashboard module.

---

## Features

### Citizen

- Track Complaint by ID
- View Complaint Status

### Admin

- Total Complaints
- Pending Complaints
- Resolved Complaints
- Category Statistics
- Status Statistics

### Government

- Dashboard Analytics
- Complaint Monitoring

---

## Architecture

DashboardController

↓

DashboardService

↓

DashboardServiceImpl

↓

ComplaintRepository

↓

MySQL

---

## APIs

- GET /api/dashboard/stats
- GET /api/dashboard/categories
- GET /api/dashboard/status
- GET /api/complaints/track/{id}

---

## Status

Phase 15.1

Completed
