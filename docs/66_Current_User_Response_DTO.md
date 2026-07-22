# Current User Response DTO

## Objective

Define the response model returned by the authenticated user endpoint.

---

## Class

CurrentUserResponse

---

## Fields

| Field       | Type          |
| ----------- | ------------- |
| id          | Long          |
| fullName    | String        |
| email       | String        |
| phoneNumber | String        |
| role        | String        |
| createdAt   | LocalDateTime |

---

## Purpose

This DTO transfers authenticated user profile information from the Service layer to the Controller without exposing the User entity directly.

---

## Benefits

- Hides entity implementation.
- Prevents exposing sensitive fields such as password.
- Keeps API responses clean.
- Follows DTO design principles.

---

**End of Current User Response DTO**
