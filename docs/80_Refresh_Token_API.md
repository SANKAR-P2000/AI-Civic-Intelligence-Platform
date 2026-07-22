# Refresh Token API

## Endpoint

POST /api/auth/refresh

---

## Purpose

Generate a new JWT Access Token using a valid Refresh Token.

---

## Request

```json
{
  "refreshToken": "UUID_REFRESH_TOKEN"
}
```

---

## Response

```json
{
  "accessToken": "JWT_ACCESS_TOKEN"
}
```

---

## Validation

- Refresh Token must exist.
- Refresh Token must not be expired.
- Refresh Token must not be revoked.

---

## Status

✅ Completed
