# AI Civic Intelligence Platform (AICIP)

# JWT Authentication Architecture

---

## Purpose

JSON Web Token (JWT) provides stateless authentication for REST APIs.

After a successful login, the server generates a signed JWT and sends it to the client. The client includes this token in the Authorization header for future requests.

---

## Authentication Flow

```
Client

↓

POST /api/users/login

↓

Spring Boot

↓

Validate Email & Password

↓

Generate JWT

↓

Return JWT Token

↓

Client Stores Token

↓

Future Requests

Authorization: Bearer <JWT>

↓

JWT Validation

↓

Protected API Access
```

---

## JWT Components

A JWT consists of three parts:

```
Header

Payload

Signature
```

Example:

```
xxxxx.yyyyy.zzzzz
```

---

## Header

Contains metadata.

Example:

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

---

## Payload

Contains application data.

Example:

```json
{
  "sub": "anand@gmail.com",
  "role": "CITIZEN",
  "iat": 1753000000,
  "exp": 1753003600
}
```

---

## Signature

Protects the token from modification.

Generated using:

- Secret Key
- Header
- Payload

---

## Benefits

- Stateless Authentication
- No server-side session
- Secure
- Fast
- Industry Standard

---

## JWT Workflow

```
Login

↓

JWT Generated

↓

Client Stores JWT

↓

Bearer Token

↓

Protected APIs

↓

JWT Validation
```

---

## Future Classes

- JwtService
- JwtAuthenticationFilter
- SecurityConfiguration Update

---

## Summary

JWT enables secure, stateless authentication for the AI Civic Intelligence Platform by replacing repeated credential verification with signed access tokens.

---

**End of JWT Architecture**
