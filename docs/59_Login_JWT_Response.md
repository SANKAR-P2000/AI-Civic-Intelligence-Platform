# Login JWT Response

## Purpose

After successful authentication, the backend generates a JWT and returns it to the client.

The client stores this token and sends it in the `Authorization` header for all protected API requests.

---

## Login Flow

```
Email + Password
        │
        ▼
Authentication
        │
        ▼
JwtService.generateToken()
        │
        ▼
JWT Token
        │
        ▼
LoginResponse
```

---

## Login Response

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

## Purpose of the Token

The client sends the JWT with every protected request.

Example:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

Spring Security validates this token before allowing access.

---

**End of Login JWT Response**
