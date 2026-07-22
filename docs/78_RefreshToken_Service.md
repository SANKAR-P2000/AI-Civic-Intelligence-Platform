# RefreshToken Service

## Objective

Implement the business logic for Refresh Tokens.

---

## Responsibilities

- Generate Refresh Tokens.
- Validate Refresh Tokens.
- Delete Refresh Tokens during logout.

---

## Methods

### createRefreshToken(User user)

Creates a new Refresh Token.

---

### verifyRefreshToken(String token)

Checks:

- Exists
- Not revoked
- Not expired

Returns a valid Refresh Token.

---

### deleteByUser(User user)

Deletes all Refresh Tokens for the specified user.

---

## Refresh Token Lifetime

7 Days

---

## Benefits

- Secure session management.
- Enables logout.
- Supports token renewal.
- Production-ready authentication.

---

## Status

✅ Completed
