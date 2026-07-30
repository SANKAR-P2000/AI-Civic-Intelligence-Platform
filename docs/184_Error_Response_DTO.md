# Phase 23.2 – ErrorResponse DTO

## Objective

Create a standard error response object for all REST APIs.

---

## Purpose

Provide consistent error responses throughout the application.

---

## Fields

- timestamp
- status
- error
- message
- path

---

## Example

```json
{
  "timestamp": "2026-07-30T14:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Complaint not found",
  "path": "/api/admin/complaints/9999"
}
```

---

## Benefits

- Standard API response
- Better frontend integration
- Easier debugging
- Cleaner code
- Professional REST API design

---

## Status

Completed
