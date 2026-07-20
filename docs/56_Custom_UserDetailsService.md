# Custom UserDetailsService

## Purpose

Spring Security requires a `UserDetailsService` implementation to load user information from the application's database.

This project provides `CustomUserDetailsService` to retrieve users by email.

---

## Responsibilities

- Load user by email
- Throw exception if user does not exist
- Convert User entity into Spring Security UserDetails

---

## Flow

```
JWT
 │
 ▼
Extract Email
 │
 ▼
loadUserByUsername()
 │
 ▼
UserRepository
 │
 ▼
Database
 │
 ▼
UserDetails
```

---

## Advantages

- Integrates application users with Spring Security.
- Reuses existing User entity.
- Centralizes authentication lookup.

---

**End of Custom UserDetailsService**
