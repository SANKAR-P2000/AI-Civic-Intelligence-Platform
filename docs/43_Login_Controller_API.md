# AI Civic Intelligence Platform (AICIP)

# Login Controller API

---

## Purpose

The Login Controller receives login requests from the client and forwards them to the service layer for authentication.

---

## Endpoint

POST /api/users/login

---

## Request Body

```json
{
  "email": "anand@gmail.com",
  "password": "password123"
}
```

---

## Success Response

HTTP Status

200 OK

```json
{
  "id": 3,
  "fullName": "Anand Kumar",
  "email": "anand@gmail.com",
  "phoneNumber": "9876543212",
  "role": "CITIZEN",
  "loginTime": "2026-07-20T15:30:00"
}
```

---

## Validation

The request is validated using:

- @Valid
- @NotBlank
- @Email

---

## Flow

Client

↓

UserController

↓

UserService

↓

Password Verification

↓

LoginResponse

---

## Benefits

- Clean REST API
- Separation of concerns
- Automatic validation
- Secure authentication flow

---

**End of Login Controller API**
