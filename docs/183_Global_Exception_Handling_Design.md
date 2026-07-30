# Phase 23 – Global Exception Handling

## Objective

Implement centralized exception handling for the entire application.

---

## Purpose

Provide consistent and meaningful error responses for all REST APIs.

---

## Exception Types

- Resource Not Found
- Validation Errors
- Email Already Exists
- Authentication Failure
- Access Denied
- Runtime Exception

---

## Components

- ErrorResponse DTO
- GlobalExceptionHandler
- @ControllerAdvice

---

## Benefits

- Consistent API responses
- Cleaner controllers
- Centralized exception handling
- Better frontend integration
- Easier debugging

---

## Output Example

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

## Status

Design Completed
