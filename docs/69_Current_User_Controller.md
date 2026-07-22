# Current User Controller

## Objective

Create a REST endpoint that returns the currently authenticated user's profile.

---

## Endpoint

GET /api/users/me

---

## Authentication

Required

Authorization Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Process

1. Receive authenticated request.
2. Read Authentication object.
3. Extract authenticated email.
4. Call UserService.
5. Return CurrentUserResponse.

---

## Response

```json
{
  "id": 3,
  "fullName": "Anand Kumar",
  "email": "anand@gmail.com",
  "phoneNumber": "9876543212",
  "role": "CITIZEN",
  "createdAt": "2026-07-20T14:26:53"
}
```

---

## Benefits

- No email is passed by the client.
- User identity comes from the validated JWT.
- Prevents users from requesting another user's profile by changing a request parameter.

---

**Status**

✅ Completed
