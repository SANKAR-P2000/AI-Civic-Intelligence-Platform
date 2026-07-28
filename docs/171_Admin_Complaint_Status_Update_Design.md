# Phase 21 – Admin Complaint Status Update Module

## Objective

Allow administrators to update complaint statuses securely.

---

## Complaint Lifecycle

PENDING
↓

UNDER_REVIEW
↓

IN_PROGRESS
↓

RESOLVED

OR

UNDER_REVIEW
↓

REJECTED

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

- Complaint must exist
- Status must be valid
- Only ADMIN can update status

---

## Benefits

- Complaint workflow management
- Better tracking
- Transparency
- Accurate dashboard statistics

---

## Status

Design Completed
