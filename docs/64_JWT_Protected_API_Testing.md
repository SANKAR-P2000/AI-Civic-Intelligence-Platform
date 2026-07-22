# JWT Protected API Testing

## Objective

Verify that protected APIs require a valid JWT token.

---

## Endpoint

GET /api/users/profile

---

## Test Case 1

### Without JWT

Request:

GET /api/users/profile

Expected:

HTTP 401 Unauthorized

Result:

PASS

---

## Test Case 2

### With JWT

Header

Authorization: Bearer <JWT_TOKEN>

Expected:

HTTP 200 OK

Response

Authenticated User Profile

Result:

PASS

---

## Conclusion

JWT authentication successfully protects secured REST APIs.

Only authenticated users with a valid JWT token can access protected endpoints.

---

**End of JWT Protected API Testing**
