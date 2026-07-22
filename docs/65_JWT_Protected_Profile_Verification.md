# JWT Protected Profile Verification

## Objective

Verify that JWT authentication successfully protects REST endpoints.

---

# Endpoint

**GET**

```
/api/users/profile
```

---

# Authentication

This endpoint requires a valid JWT access token.

Request Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

# Authentication Flow

```
User Login
      │
      ▼
POST /api/users/login
      │
      ▼
JWT Token Generated
      │
      ▼
Client Stores JWT Token
      │
      ▼
Client Sends

Authorization:
Bearer <JWT_TOKEN>

      │
      ▼
JwtAuthenticationFilter
      │
      ▼
Extract JWT
      │
      ▼
Extract Email
      │
      ▼
Load User Details
      │
      ▼
Validate JWT
      │
      ▼
SecurityContext Updated
      │
      ▼
Protected API Executes
```

---

# Request

```
GET /api/users/profile
```

Header

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

# Response

HTTP Status

```
200 OK
```

Body

```
Authenticated User Profile
```

---

# Validation Performed

The JwtAuthenticationFilter performs the following steps:

- Read Authorization header.
- Verify "Bearer " prefix.
- Extract JWT token.
- Extract username (email) from JWT.
- Load user from database.
- Validate JWT signature.
- Verify token expiration.
- Create Authentication object.
- Store Authentication in SecurityContextHolder.
- Allow request to continue.

---

# Security Verification

Without JWT

```
401 Unauthorized
```

With Valid JWT

```
200 OK
```

This confirms that the endpoint is protected by Spring Security.

---

# Technologies Used

- Spring Boot
- Spring Security
- JWT (JJWT)
- BCrypt
- MySQL
- Postman

---

# Result

JWT Authorization has been successfully verified.

Protected APIs are accessible only after successful authentication using a valid JWT token.

---

**Status**

✅ Completed Successfully
