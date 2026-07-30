# Phase 22.2 – Analytics Response DTO

## Objective

Create a reusable response DTO for administrator analytics APIs.

---

## Purpose

Represent analytics data in a common format.

---

## Fields

- label
- count

---

## Used By

- Category Analytics
- Status Analytics
- Location Analytics
- Date Analytics

---

## Example

```json
[
  {
    "label": "GARBAGE",
    "count": 12
  },
  {
    "label": "ROAD_DAMAGE",
    "count": 8
  }
]
```

---

## Benefits

- Reusable DTO
- Less code duplication
- Easy frontend chart integration
- Consistent API responses

---

## Status

Completed
