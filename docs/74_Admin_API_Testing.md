# Admin API Testing

## Objective

Verify that only users with the ADMIN role can access administrator-only endpoints using Spring Security Role-Based Authorization.

---

# Endpoint

```
GET /api/admin/dashboard
```

---

# Authentication

Authorization Header

```
Bearer <JWT_TOKEN>
```

---

# Test Case 1

## User Role

```
CITIZEN
```

### Expected

```
403 Forbidden
```

### Actual

```
403 Forbidden
```

### Result

PASS ✅

---

# Test Case 2

## User Role

```
ADMIN
```

### Login Request

```
POST /api/users/login
```

Request

```json
{
  "email": "sankar2@gmail.com",
  "password": "pass123444"
}
```

---

### Login Response

```
200 OK
```

Example

```json
{
  "email": "sankar2@gmail.com",
  "role": "ADMIN",
  "token": "<JWT_TOKEN>"
}
```

---

### Admin API Request

```
GET /api/admin/dashboard
```

Authorization

```
Bearer <JWT_TOKEN>
```

---

### Response

```
200 OK
```

Body

```
Welcome to the Admin Dashboard
```

---

## Authorization Flow

```
Client
      │
      ▼
Login
      │
      ▼
JWT Generated
      │
      ▼
Bearer Token
      │
      ▼
JwtAuthenticationFilter
      │
      ▼
Validate JWT
      │
      ▼
Extract User Role
      │
      ▼
ROLE_ADMIN
      │
      ▼
@PreAuthorize("hasRole('ADMIN')")
      │
      ▼
Access Granted
      │
      ▼
Controller
```

---

## Benefits

- Prevents unauthorized access.
- Restricts administrator APIs.
- Uses Spring Security Role-Based Authorization.
- Supports secure enterprise applications.

---

## Status

✅ Completed
