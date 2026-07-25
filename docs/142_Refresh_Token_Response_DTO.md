# Refresh Token Response DTO

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

18.3 – Refresh Token Response DTO

---

# Objective

Return a new JWT Access Token after validating a Refresh Token.

---

# Class

RefreshTokenResponse

Package:

```
com.sankar.aicip.dto.response
```

---

# Fields

| Field        | Type   | Description                      |
| ------------ | ------ | -------------------------------- |
| token        | String | Newly generated JWT Access Token |
| refreshToken | String | Existing valid Refresh Token     |

---

# Example Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "91f74462-a209-4b9a-ac14-65e0abbbd168"
}
```

---

# Benefits

- Simple API response
- Stateless JWT authentication
- Allows clients to continue authenticated sessions without logging in again

---

# Status

Completed
