# AI Civic Intelligence Platform (AICIP)

# User Service Contract

---

## Purpose

The `UserService` interface defines the business operations related to users.

It acts as the contract between the Controller layer and the Service Implementation.

---

## Current Methods

### registerUser()

```java
UserResponse registerUser(UserRegistrationRequest request);
```

### Input

- UserRegistrationRequest

### Output

- UserResponse

---

## Responsibilities

- Validate registration data
- Check duplicate email
- Encrypt password
- Save user
- Return response DTO

---

## Benefits

- Loose coupling
- Better architecture
- Easier testing
- Easier maintenance

---

**End of User Service Contract**
