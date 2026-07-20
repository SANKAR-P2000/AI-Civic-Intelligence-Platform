# JWT Login Postman Test

## Objective

Verify that the login API authenticates the user and returns a JWT.

---

## Endpoint

POST /api/users/login

---

## Headers

Content-Type: application/json

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
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## Verification Checklist

- Login succeeds.
- User information is returned.
- JWT token is included in the response.
- HTTP Status is 200 OK.

---

**End of JWT Login Postman Test**
