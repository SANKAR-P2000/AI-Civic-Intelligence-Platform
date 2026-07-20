# AI Civic Intelligence Platform (AICIP)

# User Service Implementation

---

## Purpose

`UserServiceImpl` contains the business logic for user-related operations.

It implements the `UserService` interface.

---

## Current Business Logic

### registerUser()

Responsibilities:

1. Receive registration request.
2. Create a User entity.
3. Copy request data into the entity.
4. Assign the default role (`CITIZEN`).
5. Save the user using `UserRepository`.
6. Convert the saved entity into `UserResponse`.
7. Return the response.

---

## Spring Concepts Used

- @Service
- Dependency Injection
- Constructor Injection
- Spring Data JPA
- DTO to Entity Mapping
- Entity to DTO Mapping

---

## Current Limitations

This initial implementation does not yet include:

- Duplicate email validation
- Password encryption
- Bean validation
- Exception handling

These features will be added in upcoming phases.

---

**End of User Service Implementation**
