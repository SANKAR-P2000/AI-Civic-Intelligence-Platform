# Phase 24.6 – Exception Logging

## Objective

Implement logging inside GlobalExceptionHandler.

---

# Why?

Every exception should be logged before returning the API response.

Benefits:

- Debugging
- Monitoring
- Root Cause Analysis
- Production Support

---

# Log Levels

INFO

Normal application flow.

WARN

Client mistakes.

Examples:

- Validation errors
- Resource not found
- Duplicate email
- Invalid credentials

ERROR

Unexpected server failures.

Examples:

- NullPointerException
- Database failure
- RuntimeException

---

# Security

Never log:

- Password
- JWT
- Refresh Token
- Secret Key

Log:

- Exception message
- Request URI
- Stack trace (ERROR only)

---

Status

Completed
