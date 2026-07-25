# Refresh Token Logout Service

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

18.6.2 – Refresh Token Logout Service

---

# Objective

Invalidate the user's active Refresh Token during logout.

---

# Service Method

```java
void logout(String refreshToken);
```

---

# Workflow

Client

↓

Send Refresh Token

↓

Find Refresh Token

↓

Delete Refresh Token

↓

Logout Successful

---

# Benefits

- Prevents reuse of Refresh Tokens
- Forces user to log in again
- Improves application security

---

# Status

Completed
