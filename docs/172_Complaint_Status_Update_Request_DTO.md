# Phase 21.2 – Complaint Status Update Request DTO

## Objective

Receive the new complaint status from administrators.

---

## DTO

ComplaintStatusUpdateRequest

---

## Field

| Field  | Type            |
| ------ | --------------- |
| status | ComplaintStatus |

---

## Validation

@NotNull

---

## Example

```json
{
  "status": "UNDER_REVIEW"
}
```

---

## Status

Completed
