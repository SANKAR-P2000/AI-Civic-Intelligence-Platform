# Phase 21.4 – Admin Complaint Status Update Controller

## Objective

Provide an endpoint for administrators to update complaint status.

---

## Endpoint

PUT /api/admin/complaints/{complaintId}/status

---

## Authorization

ADMIN only

---

## Request Body

```json
{
  "status": "UNDER_REVIEW"
}
```

---

## Response

Updated AdminComplaintResponse

---

## Validation

- Complaint ID must exist.
- Status must be a valid ComplaintStatus enum.
- Only ADMIN can access.

---

## Status

Completed
