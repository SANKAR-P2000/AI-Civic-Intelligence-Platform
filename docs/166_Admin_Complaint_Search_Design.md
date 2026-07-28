# Phase 20.7 – Admin Complaint Search Module

## Objective

Provide administrators with a keyword-based complaint search feature.

---

## Search Fields

- Complaint Title
- Complaint Description
- Complaint Location
- Citizen Name
- Citizen Email

---

## Endpoint

GET /api/admin/complaints/search?keyword=garbage

---

## Security

ADMIN only

---

## Response

List<AdminComplaintResponse>

---

## Benefits

- Faster complaint lookup
- Better complaint management
- Improved administrator productivity

---

## Status

Design Completed
