# Phase 23.7 – Authentication & Access Exception Handling

## Objective

Handle authentication and authorization related exceptions.

---

## Exceptions

- BadCredentialsException
- AccessDeniedException

---

## HTTP Status

401 Unauthorized

403 Forbidden

---

## Purpose

Return consistent security error responses for authentication and authorization failures.

---

## Example

### Authentication Failure

```json
{
  "timestamp": "2026-07-30T16:30:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid email or password.",
  "path": "/api/users/login"
}
```

### Authorization Failure

```json
{
  "timestamp": "2026-07-30T16:31:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access denied.",
  "path": "/api/admin/analytics/category"
}
```

---

## Status

Completed
