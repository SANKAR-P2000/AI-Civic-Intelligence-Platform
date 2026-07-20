# AI Civic Intelligence Platform (AICIP)

# Invalid Credentials Exception

---

## Purpose

The `InvalidCredentialsException` is a custom exception used during user authentication.

It is thrown when:

- The email does not exist.
- The password is incorrect.

---

## Why Use a Custom Exception?

Using a custom exception provides:

- Clear business logic.
- Better error handling.
- Appropriate HTTP status codes.
- Easier maintenance.

---

## Usage

```java
throw new InvalidCredentialsException(
    "Invalid email or password."
);
```

---

## Benefits

- Avoids generic RuntimeException.
- Makes authentication errors easier to identify.
- Improves API design.
- Works with GlobalExceptionHandler.

---

## Future Enhancements

This exception can later include:

- Account Locked
- Account Disabled
- Too Many Login Attempts

---

**End of Invalid Credentials Exception**
