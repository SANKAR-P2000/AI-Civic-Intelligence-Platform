# AI Civic Intelligence Platform (AICIP)

# Global Exception Handler

---

## Purpose

A Global Exception Handler catches exceptions thrown anywhere in the application and converts them into consistent API responses.

---

## Annotation Used

### @RestControllerAdvice

Handles exceptions globally for all REST controllers.

---

## @ExceptionHandler

Maps a specific exception to a custom response.

Example:

```java
@ExceptionHandler(EmailAlreadyExistsException.class)
```

---

## Current Response

```json
{
  "status": 409,
  "message": "Email already exists.",
  "timestamp": "2026-07-20T13:45:00"
}
```

---

## HTTP Status

409 Conflict

This status indicates that the request conflicts with the current state of the resource.

---

## Benefits

- Consistent error responses
- Cleaner controller and service code
- Easier maintenance
- Professional REST API design

---

**End of Global Exception Handler**
