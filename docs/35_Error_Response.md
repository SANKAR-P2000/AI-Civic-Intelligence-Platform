# AI Civic Intelligence Platform (AICIP)

# Error Response

---

## Purpose

The `ErrorResponse` class provides a standardized JSON structure for API error responses.

Instead of returning Spring Boot's default error format, the application returns a clean and consistent response.

---

## Why Do We Need It?

Without `ErrorResponse`, Spring Boot returns a large response containing:

- Stack trace
- Internal class names
- Exception details
- Debug information

These responses are useful for developers but should not be exposed to API consumers.

---

## Current Structure

```json
{
  "status": 409,
  "message": "Email already exists.",
  "timestamp": "2026-07-20T13:45:00"
}
```

---

## Fields

| Field | Description |
|--------|-------------|
| status | HTTP status code |
| message | Human-readable error message |
| timestamp | Date and time when the error occurred |

---

## Java Class

```java
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

}
```

---

## Usage

The `GlobalExceptionHandler` creates an `ErrorResponse` object whenever a handled exception occurs.

Example:

```java
ErrorResponse errorResponse =
        new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
```

---

## Benefits

- Consistent API responses
- Cleaner frontend integration
- No stack trace exposure
- Easier debugging
- Professional REST API design

---

## Future Enhancements

In later phases, additional fields can be added:

- errorCode
- path
- validationErrors
- requestId

These enhancements make the API even more suitable for enterprise applications.

---

**End of Error Response**