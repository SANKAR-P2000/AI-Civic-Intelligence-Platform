# JWT Service Architecture

## Purpose

`JwtService` is the central component responsible for all JWT-related operations.

Instead of generating and validating tokens throughout the application, all JWT logic is centralized in a single service.

---

## Responsibilities

- Generate JWT Access Tokens
- Extract Email (Username) from JWT
- Validate JWT Signature
- Check Token Expiration
- Parse JWT Claims

---

## Advantages

- Single Responsibility Principle (SRP)
- Easy to maintain
- Reusable across controllers and filters
- Easier testing
- Centralized security logic

---

## Package Structure

```
security
└── jwt
      └── JwtService.java
```

---

## Future Methods

The service will contain:

- generateToken()
- extractUsername()
- extractExpiration()
- extractClaim()
- isTokenValid()
- isTokenExpired()

---

## Flow

```
Login Request
      │
      ▼
UserService
      │
      ▼
JwtService.generateToken()
      │
      ▼
JWT Token
      │
      ▼
Client
```

---

This service will become the core authentication component of the AI Civic Intelligence Platform.
