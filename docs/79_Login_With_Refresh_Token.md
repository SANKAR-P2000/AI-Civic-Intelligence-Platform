# Login With Refresh Token

## Objective

Extend the login process to return both an Access Token and a Refresh Token.

---

## Login Flow

User Login

↓

Validate Credentials

↓

Generate JWT Access Token

↓

Generate Refresh Token

↓

Store Refresh Token

↓

Return Both Tokens

---

## Login Response

- User Information
- Access Token
- Refresh Token

---

## Benefits

- Supports automatic token renewal.
- Improves user experience.
- Reduces repeated logins.
- Enables secure logout.

---

## Status

✅ Completed
