# Phase 12.5 – Current Authenticated User API

## Objective

Implement a secure REST API that returns the profile of the currently authenticated user using the JWT token.

Unlike traditional applications where the client sends the user ID or email, this endpoint identifies the user from the validated JWT stored in the Authorization header.

---

# Architecture

```
Client
   │
   ▼
GET /api/users/me
   │
   ▼
Authorization: Bearer <JWT_TOKEN>
   │
   ▼
Spring Security
   │
   ▼
JwtAuthenticationFilter
   │
   ▼
JWT Validation
   │
   ▼
SecurityContextHolder
   │
   ▼
Authentication
   │
   ▼
UserController
   │
   ▼
UserService
   │
   ▼
UserRepository
   │
   ▼
MySQL Database
   │
   ▼
CurrentUserResponse
   │
   ▼
JSON Response
```

---

# Step 1 — Create CurrentUserResponse DTO

## File

```
backend/src/main/java/com/sankar/aicip/dto/response/CurrentUserResponse.java
```

## Purpose

Create a dedicated DTO to return authenticated user information without exposing the User entity.

---

## Fields

| Field       | Type          |
| ----------- | ------------- |
| id          | Long          |
| fullName    | String        |
| email       | String        |
| phoneNumber | String        |
| role        | String        |
| createdAt   | LocalDateTime |

---

## Benefits

- Hides entity implementation.
- Prevents password exposure.
- Follows DTO design pattern.
- Returns only required fields.

---

# Step 2 — Update UserService Interface

## File

```
backend/src/main/java/com/sankar/aicip/service/UserService.java
```

Add

```java
CurrentUserResponse getCurrentUser(String email);
```

---

## Purpose

Define the business contract for retrieving the authenticated user's profile.

---

# Step 3 — Implement UserService

## File

```
backend/src/main/java/com/sankar/aicip/service/impl/UserServiceImpl.java
```

Implemented

```java
@Override
public CurrentUserResponse getCurrentUser(String email)
```

---

## Process

1. Receive authenticated user's email.
2. Search database.
3. Throw exception if user not found.
4. Create DTO.
5. Copy entity values.
6. Return DTO.

---

## Database Query

```
findByEmail(email)
```

---

## Mapping

```
User Entity
        │
        ▼
CurrentUserResponse DTO
```

Mapped Fields

```
id
fullName
email
phoneNumber
role
createdAt
```

---

# Step 4 — Update UserController

## File

```
backend/src/main/java/com/sankar/aicip/controller/UserController.java
```

Created endpoint

```
GET /api/users/me
```

Implementation

```java
@GetMapping("/me")
public ResponseEntity<CurrentUserResponse> getCurrentUser(
        Authentication authentication) {

    String email = authentication.getName();

    CurrentUserResponse response =
            userService.getCurrentUser(email);

    return ResponseEntity.ok(response);
}
```

---

## Authentication Object

Spring Security automatically injects

```
Authentication authentication
```

The authenticated user's email is obtained using

```java
authentication.getName()
```

No request parameter is required.

---

# Authentication Flow

```
User Login
      │
      ▼
JWT Generated
      │
      ▼
Client Stores JWT
      │
      ▼
GET /api/users/me
      │
      ▼
Authorization:
Bearer <JWT>
      │
      ▼
JwtAuthenticationFilter
      │
      ▼
Extract JWT
      │
      ▼
Validate Signature
      │
      ▼
Extract Email
      │
      ▼
Load UserDetails
      │
      ▼
SecurityContextHolder
      │
      ▼
Authentication
      │
      ▼
authentication.getName()
      │
      ▼
UserService
      │
      ▼
Repository
      │
      ▼
Database
      │
      ▼
CurrentUserResponse
      │
      ▼
HTTP 200 OK
```

---

# Endpoint

```
GET /api/users/me
```

---

# Authorization Header

```
Authorization:
Bearer <JWT_TOKEN>
```

---

# Postman Testing

## Test Case 1

Without JWT

Request

```
GET /api/users/me
```

Expected

```
401 Unauthorized
```

Result

```
PASS
```

---

## Test Case 2

With Valid JWT

Header

```
Authorization:
Bearer <JWT_TOKEN>
```

Expected

```
HTTP 200 OK
```

Example Response

```json
{
  "id": 3,
  "fullName": "Anand Kumar",
  "email": "anand@gmail.com",
  "phoneNumber": "9876543212",
  "role": "CITIZEN",
  "createdAt": "2026-07-20T14:26:53.269364"
}
```

Result

```
PASS
```

---

# Security Advantages

- User identity comes from the validated JWT.
- Client cannot access another user's profile by changing an ID.
- No email or user ID is passed in the request.
- Password is never exposed.
- Authentication is handled by Spring Security.
- Business logic remains inside the Service layer.
- Controller remains lightweight.
- Follows layered architecture.

---

# Project Files Created

```
CurrentUserResponse.java
```

```
UserService.java
```

```
UserServiceImpl.java
```

```
UserController.java
```

---

# Documentation Files

```
docs/65_Current_User_Response_DTO.md
```

```
docs/66_Current_User_Service_Interface.md
```

```
docs/67_Current_User_Service_Implementation.md
```

```
docs/68_Current_User_Controller.md
```

```
docs/69_Current_User_API_Testing.md
```

---

# Phase 12.5 Summary

## Features Completed

- CurrentUserResponse DTO
- Service Interface
- Service Implementation
- User Controller Endpoint
- JWT Authentication Integration
- Current Authenticated User Retrieval
- Postman Testing
- Documentation

---

# Final Result

The application successfully retrieves the currently authenticated user's profile using the JWT token.

The endpoint does not require the client to send an email, user ID, or any identifying information.

Spring Security authenticates the request, extracts the user identity from the JWT, loads the user from the database, and returns a secure DTO containing the authenticated user's profile.

---

## Status

**Phase 12.5 – Current Authenticated User API**

✅ Successfully Completed
