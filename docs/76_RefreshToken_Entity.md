# RefreshToken Entity

## Objective

Create a JPA entity to persist refresh tokens for authenticated users.

---

## Table

refresh_tokens

---

## Fields

- id
- token
- expiryDate
- revoked
- user

---

## Relationship

One User

↓

Many Refresh Tokens

---

## Features

- Stores refresh tokens.
- Supports multiple devices.
- Enables logout.
- Supports token revocation.
- Used for access token renewal.

---

## Status

✅ Completed
