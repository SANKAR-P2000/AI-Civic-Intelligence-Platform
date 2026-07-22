# Token Revocation Strategy

## Objective

Understand different strategies for invalidating Refresh Tokens.

---

## Strategy Used

Delete Refresh Tokens from the database during logout.

### Advantages

- Simple implementation.
- Removes active sessions.
- Easy to maintain.

---

## Enterprise Strategy

Mark tokens as revoked.

Example

revoked = true

### Advantages

- Keeps session history.
- Supports auditing.
- Detects replay attacks.
- Better for large-scale applications.

---

## Project Decision

This project uses token deletion because it is simpler and appropriate for learning purposes.

---

## Status

✅ Completed
