# JWT Token Generation

## Purpose

The `generateToken()` method creates a signed JWT after successful user authentication.

---

## Process

1. Receive authenticated user details.
2. Set subject as user's email.
3. Set issued time.
4. Set expiration time.
5. Sign using the secret key.
6. Return the compact JWT string.

---

## JWT Claims

Current claims:

- Subject
- Issued At
- Expiration

---

## Signing Algorithm

The token is signed using the application's secret key.

Only the server can generate valid tokens.

---

## Flow

```
Login
   │
   ▼
JwtService.generateToken()
   │
   ▼
Signed JWT
   │
   ▼
Client
```

---

The generated token will later be returned from the Login API and used for authenticated requests.

---

**End of JWT Token Generation**
