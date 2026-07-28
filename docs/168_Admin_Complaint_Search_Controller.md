# Phase 20.7.4 – Admin Complaint Search Controller

## Objective

Expose complaint search functionality for administrators.

---

## Endpoint

GET /api/admin/complaints/search?keyword={keyword}

---

## Request Parameter

keyword

---

## Authorization

ADMIN only

---

## Response

List<AdminComplaintResponse>

---

## Example

GET /api/admin/complaints/search?keyword=garbage

Returns all complaints matching the keyword in:

- Title
- Description
- Location
- Citizen Name
- Citizen Email

---

## Status

Completed
