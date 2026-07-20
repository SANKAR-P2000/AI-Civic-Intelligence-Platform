# AI Civic Intelligence Platform (AICIP)

# User Response DTO

---

## Purpose

The `UserResponse` DTO is returned to the client after successful user operations.

It exposes only safe information and hides sensitive fields.

---

## Fields

| Field       | Description       |
| ----------- | ----------------- |
| id          | User ID           |
| fullName    | User's full name  |
| email       | Email address     |
| phoneNumber | Mobile number     |
| role        | User role         |
| createdAt   | Registration date |

---

## Hidden Fields

The following fields are intentionally excluded:

- Password

---

## Why?

Returning passwords or other sensitive information creates security risks.

The Response DTO ensures only the required information is sent back to the client.

---

## Flow

Database

↓

User Entity

↓

UserResponse DTO

↓

Client

---

## Benefits

- Improved Security
- Better API Design
- Loose Coupling
- Easier Maintenance

---

**End of User Response DTO**
