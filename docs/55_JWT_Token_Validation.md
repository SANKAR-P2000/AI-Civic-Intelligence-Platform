# JWT Token Validation

## Purpose

After a JWT is generated, every protected request must verify that the token is still valid.

The JwtService provides methods to perform this validation.

---

## Implemented Methods

### extractExpiration()

Returns the expiration date stored in the JWT.

---

### isTokenExpired()

Checks whether the current date is after the token expiration.

Returns:

- true → Token expired
- false → Token still valid

---

### isTokenValid()

Validates the token by checking:

1. Username inside the JWT matches the authenticated user.
2. Token has not expired.

Both conditions must be true for successful authentication.

---

## Validation Flow

```
JWT
 │
 ▼
Extract Username
 │
 ▼
Compare User
 │
 ▼
Check Expiration
 │
 ▼
Valid Token
```

---

## Security Benefits

- Prevents expired tokens from being used.
- Prevents token misuse with different users.
- Centralizes JWT validation logic.

---

**End of JWT Token Validation**
