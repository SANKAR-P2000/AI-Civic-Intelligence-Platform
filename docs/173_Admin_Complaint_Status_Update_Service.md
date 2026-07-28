# Phase 21.3 – Admin Complaint Status Update Service

## Objective

Implement business logic to update complaint status.

---

## Service Method

```java
updateComplaintStatus(Long complaintId,
                      ComplaintStatusUpdateRequest request)
```

---

## Workflow

1. Find complaint by ID.
2. Throw exception if not found.
3. Update complaint status.
4. Save updated complaint.
5. Convert to AdminComplaintResponse.
6. Return response.

---

## Validation

- Complaint must exist.
- Status comes from ComplaintStatus enum.

---

## Status

Completed
