# AI Civic Intelligence Platform (AICIP)

# User Registration Request DTO

---

## Purpose

The `UserRegistrationRequest` DTO receives registration data sent by the client.

It is used instead of exposing the `User` entity directly.

---

## Fields

| Field       | Data Type | Description          |
| ----------- | --------- | -------------------- |
| fullName    | String    | User's full name     |
| email       | String    | User's email address |
| password    | String    | User's password      |
| phoneNumber | String    | User's mobile number |

---

## Why Use a Request DTO?

- Accepts only required input fields.
- Prevents direct exposure of the database entity.
- Improves security.
- Simplifies request validation.
- Makes the API easier to maintain.

---

## Flow

Client

↓

UserRegistrationRequest

↓

UserService

↓

User Entity

↓

Database

---

**End of User Registration Request DTO**
