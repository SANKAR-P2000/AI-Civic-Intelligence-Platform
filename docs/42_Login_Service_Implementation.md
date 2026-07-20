# AI Civic Intelligence Platform (AICIP)

# Login Service Implementation

---

## Purpose

The Login Service Implementation contains the business logic required to authenticate a user.

It verifies whether the user exists, validates the password using BCrypt, and returns the authenticated user's information.

The service layer acts as the bridge between the controller and the repository.

---

## Responsibilities

The `loginUser()` method performs the following tasks:

1. Receive the login request.
2. Search the database using the provided email.
3. Verify that the user exists.
4. Compare the entered password with the stored BCrypt password.
5. Return user information if authentication succeeds.
6. Throw an exception if authentication fails.

---

## Authentication Flow

Client

↓

Login Controller

↓

UserService Interface

↓

UserServiceImpl

↓

UserRepository

↓

Find User By Email

↓

PasswordEncoder.matches()

↓

Authentication Success / Failure

---

## Login Algorithm

### Step 1

Search the user by email.

```java
User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() ->
                new RuntimeException("Invalid email or password."));
```

If the email is not found, authentication fails.

---

### Step 2

Verify the password.

```java
if (!passwordEncoder.matches(
        request.getPassword(),
        user.getPassword())) {

    throw new RuntimeException("Invalid email or password.");
}
```

The `matches()` method compares:

- Plain password entered by the user.
- BCrypt hash stored in the database.

---

### Step 3

Create the response object.

```java
LoginResponse response = new LoginResponse();
```

Populate the response with user information.

---

### Step 4

Return the authenticated user's details.

The password is **never returned** to the client.

---

## Why Use PasswordEncoder.matches()?

Passwords stored using BCrypt are hashed.

Example:

Plain Password

```
password123
```

Stored Password

```
$2a$10$QW7zD1cB4yY...
```

Because the stored password is hashed, direct comparison is impossible.

Instead, Spring Security provides:

```java
passwordEncoder.matches(
        request.getPassword(),
        user.getPassword()
);
```

This securely verifies the password.

---

## Why Don't We Compare Strings?

Incorrect:

```java
request.getPassword().equals(user.getPassword())
```

This will always fail because:

```
password123
≠
$2a$10$QW7zD1cB4yY...
```

Using `PasswordEncoder.matches()` is the correct and secure approach.

---

## Security Benefits

- BCrypt password verification.
- Passwords are never stored in plain text.
- Passwords are never returned in API responses.
- Authentication logic is centralized in the service layer.

---

## Future Improvements

In later phases, this implementation will be enhanced with:

- InvalidCredentialsException
- Global Exception Handling
- JWT Token Generation
- Refresh Token Support
- Login Audit Logging
- Account Locking After Multiple Failed Attempts

---

## Summary

The Login Service Implementation authenticates users by verifying their email and password using Spring Security's `PasswordEncoder`. It ensures secure authentication while protecting sensitive information and following the layered architecture of the application.

---

**End of Login Service Implementation**
