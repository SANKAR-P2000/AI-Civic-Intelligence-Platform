# AI Civic Intelligence Platform (AICIP)

# User Controller Design

---

## Purpose

The `UserController` exposes REST API endpoints related to user operations.

It receives HTTP requests, forwards them to the service layer, and returns HTTP responses.

---

## Base URL

```
/api/users
```

---

## Current Endpoint

### Register User

```
POST /api/users/register
```

---

## Request Body

```json
{
  "fullName": "Sankar",
  "email": "sankar@gmail.com",
  "password": "password123",
  "phoneNumber": "9876543210"
}
```

---

## Response

Returns a `UserResponse` object with HTTP Status **201 Created**.

---

## Spring Annotations Used

- `@RestController`
- `@RequestMapping`
- `@PostMapping`
- `@RequestBody`

---

## Responsibilities

- Accept HTTP requests.
- Delegate business logic to the service layer.
- Return HTTP responses.

---

**End of User Controller Design**
