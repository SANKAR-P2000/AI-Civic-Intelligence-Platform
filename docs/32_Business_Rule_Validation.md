# AI Civic Intelligence Platform (AICIP)

# Business Rule Validation

---

## Purpose

Business rule validation checks application-specific rules that cannot be validated using annotations.

---

## Current Rule

A user cannot register with an email address that already exists.

---

## Validation Flow

Client

↓

DTO Validation

↓

Business Rule Validation

↓

Save User

---

## Example

```java
if (userRepository.findByEmail(request.getEmail()).isPresent()) {
    throw new RuntimeException("Email already exists.");
}
```

---

## Why Not Use @Email?

`@Email` only checks whether the email format is valid.

It does **not** check whether the email already exists in the database.

---

## Benefits

- Prevents duplicate accounts.
- Keeps business logic inside the service layer.
- Avoids unnecessary database constraint exceptions.

---

**End of Business Rule Validation**
