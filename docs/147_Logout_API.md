# Logout API

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

18.6.3 – Logout API

---

# Endpoint

POST /api/auth/logout

---

# Request

```json
{
  "refreshToken": "cbc2e533-348e-4361-80c1-86f7bb89c9fc"
}
```

---

# Success Response

HTTP 200 OK

```
Logged out successfully.
```

---

# Workflow

User

↓

POST /api/auth/logout

↓

Refresh Token Received

↓

Delete Refresh Token

↓

Logout Successful

---

# Benefits

- Invalidates current session
- Prevents refresh token reuse
- Improves authentication security

---

# Status

Completed
