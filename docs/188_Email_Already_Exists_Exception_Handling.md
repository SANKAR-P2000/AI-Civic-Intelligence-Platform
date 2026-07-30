# Phase 23.6 – EmailAlreadyExistsException Handling

## Objective

Handle duplicate email registration using a centralized exception handler.

---

## Exception

EmailAlreadyExistsException

---

## HTTP Status

409 Conflict

---

## Purpose

Return a meaningful error response when attempting to register an email address that already exists.

---

## Example

```json
{
  "timestamp": "2026-07-30T16:00:00",
  "status": 409,
  "error": "Conflict",
  "message": "Email already exists.",
  "path": "/api/users/register"
}
```

---

## Status

Completed
