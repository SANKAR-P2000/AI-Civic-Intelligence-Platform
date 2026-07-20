# AI Civic Intelligence Platform (AICIP)

# Login Service Interface

---

## Purpose

The service interface defines the contract for user authentication.

The controller communicates with the service layer through this interface instead of directly accessing the implementation.

---

## Methods

### Register User

```java
UserResponse registerUser(UserRegistrationRequest request);
```

Creates a new user account.

---

### Login User

```java
LoginResponse loginUser(LoginRequest request);
```

Authenticates an existing user.

---

## Why Use an Interface?

- Loose coupling
- Better maintainability
- Easier testing
- Supports multiple implementations
- Follows Spring Boot best practices

---

## Architecture

Controller

↓

UserService (Interface)

↓

UserServiceImpl

↓

Repository

---

**End of Login Service Interface**
