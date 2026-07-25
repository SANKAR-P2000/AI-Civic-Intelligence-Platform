# Refresh Token Architecture

## Objective

Implement production-ready authentication using JWT Access Tokens and Refresh Tokens.

---

## Authentication Flow

Login

↓

Generate JWT Access Token

↓

Generate Refresh Token

↓

Store Refresh Token in Database

↓

Return Both Tokens

↓

Access Token Expires

↓

Client Calls Refresh Token API

↓

Server Validates Refresh Token

↓

Generate New JWT Access Token

↓

Return New Access Token

---

## APIs

### POST /api/users/login

Returns:

- Access Token
- Refresh Token

---

### POST /api/users/refresh-token

Generates a new JWT Access Token using a valid Refresh Token.

---

### POST /api/users/logout

Deletes the Refresh Token from the database.

---

## Benefits

- Better Security
- Improved User Experience
- Supports Long Sessions
- Enables Secure Logout
- Production-Ready Authentication
