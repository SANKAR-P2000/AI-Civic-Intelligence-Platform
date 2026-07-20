# JWT Login Testing

## Purpose

Verify that the login endpoint authenticates the user and returns a valid JWT.

---

## Endpoint

POST /api/users/login

---

## Request Body

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

---

## Expected Response

```json
{
  "id": 1,
  "fullName": "User",
  "email": "user@example.com",
  "phoneNumber": "9876543210",
  "role": "CITIZEN",
  "loginTime": "...",
  "token": "eyJhbGciOi..."
}
```

---

## Verification Checklist

- User credentials are valid.
- JWT token is returned.
- Login time is populated.
- User details are returned correctly.

---

**End of JWT Login Testing**
