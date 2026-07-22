# RefreshToken Repository

## Objective

Provide database access methods for Refresh Tokens.

---

## Methods

### findByToken(String token)

Returns a refresh token if it exists.

Used during access token renewal.

---

### deleteByUser(User user)

Deletes all refresh tokens belonging to a user.

Used during logout.

---

## Benefits

- Secure token lookup.
- Supports logout.
- Simplifies refresh token management.

---

## Status

✅ Completed
