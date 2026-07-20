# JWT Authentication Filter

## Purpose

`JwtAuthenticationFilter` intercepts every incoming HTTP request.

It extracts the JWT from the `Authorization` header, validates it, and authenticates the user.

---

## Responsibilities

- Read Authorization header
- Extract Bearer token
- Validate JWT
- Load user details
- Store authentication in SecurityContext

---

## Request Flow

```
Client Request
        │
        ▼
Authorization Header
        │
        ▼
JwtAuthenticationFilter
        │
        ▼
JwtService
        │
        ▼
CustomUserDetailsService
        │
        ▼
SecurityContext
        │
        ▼
Controller
```

---

## Security Benefits

- Stateless authentication
- Every request is verified independently
- No server-side session required

---

**End of JWT Authentication Filter**
