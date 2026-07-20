# Spring Security JWT Configuration

## Purpose

This configuration integrates JWT authentication with Spring Security.

---

## Features

- Stateless authentication
- JWT Authentication Filter
- Custom UserDetailsService
- BCrypt password encoder
- Public login and registration APIs
- Protected application APIs

---

## Public Endpoints

- POST /api/users/register
- POST /api/users/login

---

## Protected Endpoints

All remaining endpoints require a valid JWT.

---

## Security Flow

```
Request
   │
   ▼
JwtAuthenticationFilter
   │
   ▼
Validate JWT
   │
   ▼
Load User
   │
   ▼
SecurityContext
   │
   ▼
Controller
```

---

## Session Policy

```
STATELESS
```

Every request must contain a valid JWT.

---

**End of Spring Security JWT Configuration**
