# Refresh Token Service

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

18.4 – Refresh Token Service

---

# Objective

Implement the business logic required to generate a new JWT Access Token using a valid Refresh Token.

---

# Responsibilities

- Validate Refresh Token
- Check Expiration
- Retrieve User
- Generate New JWT Access Token
- Return RefreshTokenResponse

---

# Method

```java
RefreshTokenResponse refreshAccessToken(String refreshToken);
```

---

# Workflow

Client

↓

Send Refresh Token

↓

Validate Refresh Token

↓

Generate New JWT

↓

Return Response

---

# Benefits

- Thin Controller
- Centralized Business Logic
- Easier Maintenance
- Reusable Service Layer

---

# Status

Completed
