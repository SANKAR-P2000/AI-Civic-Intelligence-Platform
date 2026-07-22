# Phase 13 – Authentication and Session Management

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Implement a secure authentication system using Spring Security, JWT (JSON Web Token), and Refresh Tokens.

This phase ensures:

- Secure user login
- JWT-based authentication
- Role-based authorization
- Session management using Refresh Tokens
- Secure logout functionality

---

# Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- BCrypt Password Encoder
- Spring Data JPA
- MySQL
- Postman

---

# Authentication Architecture

```
                User Login
                     │
                     ▼
          Verify Email & Password
                     │
                     ▼
          Generate Access Token (JWT)
                     │
                     ▼
         Generate Refresh Token (UUID)
                     │
                     ▼
      Store Refresh Token in Database
                     │
                     ▼
        Access Protected Endpoints
                     │
                     ▼
      Access Token Expires (24 Hours)
                     │
                     ▼
      Client Sends Refresh Token
                     │
                     ▼
      Verify Refresh Token
                     │
                     ▼
      Generate New Access Token
                     │
                     ▼
         Continue User Session
                     │
                     ▼
               User Logout
                     │
                     ▼
     Delete Refresh Token From Database
                     │
                     ▼
      Old Refresh Token Becomes Invalid
```

---

# Database

## users

Stores user information.

Fields

- id
- full_name
- email
- password
- phone_number
- role
- created_at

---

## refresh_tokens

Stores active refresh tokens.

Fields

- id
- token
- expiry_date
- revoked
- user_id

Relationship

```
User (1)
     │
     │
     ▼
RefreshToken (Many)
```

---

# Authentication Flow

## 1. User Registration

Endpoint

```
POST /api/users/register
```

Features

- Validates request
- Encrypts password using BCrypt
- Saves user
- Assigns default role (CITIZEN)

---

## 2. User Login

Endpoint

```
POST /api/users/login
```

Input

```json
{
  "email": "anand@gmail.com",
  "password": "password123"
}
```

Response

```json
{
  "id": 3,
  "fullName": "Anand Kumar",
  "email": "anand@gmail.com",
  "role": "CITIZEN",
  "token": "JWT_TOKEN",
  "refreshToken": "UUID_REFRESH_TOKEN"
}
```

Process

- Validate email
- Verify BCrypt password
- Generate JWT
- Generate Refresh Token
- Save Refresh Token
- Return both tokens

---

# JWT Authorization

Protected endpoints require

```
Authorization

Bearer <JWT_TOKEN>
```

Example

```
GET /api/users/me
```

Response

```
200 OK
```

Returns authenticated user profile.

---

# Role-Based Authorization

Roles

- ADMIN
- GOVERNMENT
- CITIZEN

Examples

```
@PreAuthorize("hasRole('ADMIN')")
```

```
@PreAuthorize("hasAnyRole('ADMIN','GOVERNMENT')")
```

```
@PreAuthorize("hasRole('CITIZEN')")
```

---

# Refresh Token

Endpoint

```
POST /api/auth/refresh
```

Input

```json
{
  "refreshToken": "UUID_TOKEN"
}
```

Process

- Verify token exists
- Check expiry
- Generate new JWT
- Return new Access Token

Response

```json
{
  "accessToken": "NEW_JWT"
}
```

---

# Logout

Endpoint

```
POST /api/auth/logout
```

Input

```json
{
  "email": "anand@gmail.com"
}
```

Process

- Find user
- Delete Refresh Token
- End session

Response

```
Logout successful.
```

---

# Session Lifecycle

```
Register
     │
     ▼
Login
     │
     ├──────── Access Token
     │
     └──────── Refresh Token
                  │
                  ▼
      Stored in Database
                  │
                  ▼
Access Protected APIs
                  │
                  ▼
JWT Expires
                  │
                  ▼
Refresh API
                  │
                  ▼
New JWT Generated
                  │
                  ▼
Logout
                  │
                  ▼
Delete Refresh Token
                  │
                  ▼
Refresh Token Invalid
```

---

# Security Features

✔ BCrypt Password Encryption

✔ JWT Authentication

✔ Stateless Session

✔ Refresh Token Management

✔ Role-Based Authorization

✔ Spring Security Filters

✔ Protected APIs

✔ Custom Exception Handling

---

# APIs Implemented

| Method | Endpoint            | Description        |
| ------ | ------------------- | ------------------ |
| POST   | /api/users/register | Register User      |
| POST   | /api/users/login    | Login              |
| GET    | /api/users/profile  | Protected Profile  |
| GET    | /api/users/me       | Authenticated User |
| POST   | /api/auth/refresh   | Refresh JWT        |
| POST   | /api/auth/logout    | Logout             |

---

# Verification Completed

✔ User Registration

✔ Password Encryption

✔ JWT Generation

✔ Login Success

✔ Protected API Access

✔ Role Authorization

✔ Refresh Token Creation

✔ Refresh Token Verification

✔ Logout Success

✔ Refresh Token Deleted

✔ Old Refresh Token Rejected

---

# Learning Outcomes

After completing Phase 13, I learned:

- Spring Security fundamentals
- JWT authentication
- Stateless authentication
- BCrypt password hashing
- Refresh Token implementation
- Role-Based Authorization
- Spring Security Filter Chain
- Session management
- Secure API design
- Authentication best practices

---

# Phase Status

```
Phase 13
Authentication & Session Management

STATUS

COMPLETED
```
