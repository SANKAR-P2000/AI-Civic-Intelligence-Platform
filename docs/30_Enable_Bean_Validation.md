# AI Civic Intelligence Platform (AICIP)

# Enable Bean Validation

---

## Purpose

The `@Valid` annotation tells Spring Boot to validate the request object before executing the controller method.

---

## Why is `@Valid` Needed?

Validation annotations such as:

- `@NotBlank`
- `@Email`
- `@Size`
- `@Pattern`

are ignored unless the request object is marked with `@Valid`.

---

## Example

```java
@PostMapping("/register")
public ResponseEntity<UserResponse> registerUser(
        @Valid @RequestBody UserRegistrationRequest request) {
```

---

## Validation Flow

Client

↓

Request DTO

↓

@Valid

↓

Validation

↓

Controller

↓

Service

↓

Repository

↓

Database

---

## Benefits

- Automatic validation
- Cleaner controller code
- Prevents invalid data from reaching business logic
- Standard Spring Boot approach

---

**End of Enable Bean Validation**
