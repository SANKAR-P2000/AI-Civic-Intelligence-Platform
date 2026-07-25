# Logout Request DTO

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

18.6.1 – Logout Request DTO

---

# Objective

Receive the Refresh Token from the client during logout.

---

# Class

LogoutRequest

Package

com.sankar.aicip.dto.request

---

# Field

| Field        | Type   | Description             |
| ------------ | ------ | ----------------------- |
| refreshToken | String | Refresh Token to revoke |

---

# Example Request

```json
{
  "refreshToken": "cbc2e533-348e-4361-80c1-86f7bb89c9fc"
}
```

---

# Purpose

The Refresh Token identifies the active login session. During logout, the server revokes or deletes this token to prevent further access token generation.

---

# Status

Completed
