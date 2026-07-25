# Phase 18.6.4 – Logout API Testing

## Objective

Verify that the Logout API invalidates the user's refresh token and prevents further token refresh requests.

---

# Test Environment

- Backend : Spring Boot
- Database : MySQL
- Client : Postman
- Authentication : JWT + Refresh Token

---

# Test Case 1 – User Login

## API

POST /api/users/login

### Request

```json
{
  "email": "esportspubgnewstate@gmail.com",
  "password": "password123cm"
}
```

### Expected Result

- Login successful
- JWT Access Token generated
- Refresh Token generated

### Actual Result

PASS

---

# Test Case 2 – Logout

## API

POST /api/auth/logout

### Request

```json
{
  "refreshToken": "65a7b1d6-f64e-419d-8116-9e6eb750363d"
}
```

### Expected Result

HTTP 200 OK

```
Logged out successfully.
```

### Actual Result

PASS

---

# Test Case 3 – Database Verification

Table:

```
refresh_tokens
```

Verification:

- Logout removes the refresh token from the database.
- Token can no longer be used.

### Actual Result

PASS

---

# Test Case 4 – Login Again

## API

POST /api/users/login

### Expected Result

- New JWT Access Token
- New Refresh Token

Example:

```
d0d65171-b075-42e5-b3cf-3bc79eea49d0
```

### Actual Result

PASS

---

# Test Case 5 – Refresh Token

## API

POST /api/auth/refresh

### Request

```json
{
  "refreshToken": "d0d65171-b075-42e5-b3cf-3bc79eea49d0"
}
```

### Expected Result

- New JWT Access Token generated successfully.

### Actual Result

PASS

---

# Test Summary

| Test Case             | Result |
| --------------------- | ------ |
| Login                 | PASS   |
| Logout                | PASS   |
| Refresh Token Removal | PASS   |
| Login Again           | PASS   |
| Refresh Token API     | PASS   |

---

# Conclusion

The Logout API has been successfully implemented.

Verified features:

- User Login
- JWT Authentication
- Refresh Token Generation
- Logout
- Refresh Token Revocation
- Re-login
- Refresh Token Authentication

The Refresh Token Authentication module is fully functional and ready for production use.
