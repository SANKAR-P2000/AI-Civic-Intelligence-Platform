# AI Civic Intelligence Platform (AICIP)

# Authentication Exception Handling

---

## Purpose

Authentication errors should return meaningful HTTP responses instead of generic server errors.

---

## Current Flow

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

## Benefits

- Better REST API design.
- Consistent error responses.
- Easier debugging.
- Cleaner code.

---

## Exception Used

```java
throw new InvalidCredentialsException(
    "Invalid email or password."
);
```

---

## Next Step

The GlobalExceptionHandler catches this exception and returns HTTP 401 Unauthorized.

---

**End of Authentication Exception Handling**
