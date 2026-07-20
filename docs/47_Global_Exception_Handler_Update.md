# AI Civic Intelligence Platform (AICIP)

# Global Exception Handler Update

---

## Purpose

The Global Exception Handler has been updated to handle authentication failures using the custom `InvalidCredentialsException`.

Instead of returning a generic HTTP 500 Internal Server Error, the API now returns a meaningful HTTP 401 Unauthorized response.

---

## Exception Handled

```java
InvalidCredentialsException
```

---

## HTTP Response

```
401 Unauthorized
```

---

## Response Body

```json
{
  "status": 401,
  "message": "Invalid email or password.",
  "timestamp": "2026-07-20T16:00:00"
}
```

---

## Benefits

- Correct HTTP status code
- Cleaner API responses
- Better REST API design
- Easier debugging for clients
- Improved security by using a generic authentication message

---

## Authentication Flow

Client

↓

Login API

↓

InvalidCredentialsException

↓

GlobalExceptionHandler

↓

401 Unauthorized

---

## Summary

The application now handles authentication failures in a consistent and production-ready manner by returning HTTP 401 Unauthorized instead of HTTP 500 Internal Server Error.

---

**End of Global Exception Handler Update**
