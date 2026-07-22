# Refresh Token Architecture

## Objective

Introduce Refresh Tokens to improve authentication by allowing users to obtain new access tokens without logging in again.

---

## Tokens

### Access Token

- Used for authorization.
- Sent with every protected API request.
- Short expiration time.
- Cannot be renewed directly.

---

### Refresh Token

- Used to generate new access tokens.
- Long expiration time.
- Stored in the database.
- Can be revoked during logout.

---

## Login Flow

User Login

↓

Validate Credentials

↓

Generate Access Token

↓

Generate Refresh Token

↓

Store Refresh Token

↓

Return Both Tokens

---

## Refresh Flow

Access Token Expires

↓

Client sends Refresh Token

↓

Server validates Refresh Token

↓

Generate New Access Token

↓

Return New Access Token

---

## Logout Flow

User requests logout

↓

Refresh Token removed

↓

Session terminated

---

## Benefits

- Better user experience.
- Improved security.
- Supports multiple sessions.
- Enables secure logout.
- Production-ready authentication design.

---

## Status

✅ Completed
