# Logout API

## Endpoint

POST /api/auth/logout

---

## Purpose

Deletes all Refresh Tokens associated with a user.

---

## Request

```json
{
  "email": "user@example.com"
}
```

---

## Response

```
Logout successful.
```

---

## Logout Flow

User

↓

Logout Request

↓

Find User

↓

Delete Refresh Tokens

↓

Session Terminated

---

## Benefits

- Prevents token reuse.
- Securely ends user sessions.
- Forces re-authentication.

---

## Note

This project removes refresh tokens from the database during logout.

In enterprise applications, a common alternative is to mark tokens as revoked (`revoked = true`) instead of deleting them. This preserves session history while still preventing token reuse.

## Status

✅ Completed
