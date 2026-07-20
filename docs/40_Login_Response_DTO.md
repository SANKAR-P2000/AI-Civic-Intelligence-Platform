# AI Civic Intelligence Platform (AICIP)

# Login Response DTO

---

## Purpose

The `LoginResponse` DTO is used to send user information back to the client after a successful login.

Instead of returning the complete `User` entity, the application returns only the required information needed by the client.

---

## Why Do We Need a Response DTO?

Returning the `User` entity directly is not recommended because it contains internal fields such as the password.

A Response DTO helps:

- Hide sensitive information.
- Return only necessary data.
- Improve API security.
- Separate the API model from the database model.

---

## Fields

| Field       | Type          | Description                               |
| ----------- | ------------- | ----------------------------------------- |
| id          | Long          | Unique user identifier                    |
| fullName    | String        | User's full name                          |
| email       | String        | Registered email address                  |
| phoneNumber | String        | Registered phone number                   |
| role        | String        | User role (CITIZEN / ADMIN)               |
| loginTime   | LocalDateTime | Time when the user successfully logged in |

---

## Example Response

```json
{
  "id": 3,
  "fullName": "Anand Kumar",
  "email": "anand@gmail.com",
  "phoneNumber": "9876543212",
  "role": "CITIZEN",
  "loginTime": "2026-07-20T15:10:00"
}
```

---

## Response Flow

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

Password Verification

↓

LoginResponse DTO

↓

Client

---

## Why Don't We Return the User Entity?

The `User` entity contains sensitive information such as the password.

Even though the password is stored as a BCrypt hash, it should never be exposed in an API response.

Using a Response DTO ensures that only safe and relevant information is returned.

---

## Benefits

- Improves API security.
- Prevents exposure of sensitive data.
- Keeps API responses clean and lightweight.
- Follows REST API best practices.
- Maintains separation between database entities and API models.

---

## Future Enhancements

In future phases, the LoginResponse DTO may include:

- JWT Access Token
- Refresh Token
- Token Expiration Time
- User Permissions
- Profile Information

---

## Summary

The `LoginResponse` DTO provides a secure and structured response after successful authentication while protecting sensitive user information.

---

**End of Login Response DTO**
