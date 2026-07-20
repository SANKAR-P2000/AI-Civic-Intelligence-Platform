# AI Civic Intelligence Platform (AICIP)

# Login Module Architecture

---

## Objective

The Login Module authenticates registered users using their email and password.

Unlike registration, login does not create a new user.

Instead, it verifies whether the provided credentials are valid.

---

## Login Flow

Client

↓

POST /api/users/login

↓

UserController

↓

UserService

↓

UserRepository

↓

Find User By Email

↓

PasswordEncoder.matches()

↓

Success / Failure

---

## Authentication Steps

1. Receive email and password.
2. Search the database by email.
3. If the email does not exist, return an error.
4. Compare the entered password with the stored BCrypt hash.
5. If they match, return user information.
6. Otherwise, return an authentication error.

---

## Components

- LoginRequest DTO
- LoginResponse DTO
- UserController
- UserService
- UserRepository
- PasswordEncoder

---

## Password Verification

```java
passwordEncoder.matches(
        request.getPassword(),
        user.getPassword()
);
```

---

## Future Enhancement

After login succeeds, JWT Token authentication will be implemented.

---

**End of Login Module Architecture**
