# Current User API Testing

## Endpoint

GET /api/users/me

---

## Authentication

Bearer Token Required

---

## Test Case 1

Without JWT

Expected:

401 Unauthorized

Result:

PASS

---

## Test Case 2

With Valid JWT

Expected:

HTTP 200 OK

Response contains:

- id
- fullName
- email
- phoneNumber
- role
- createdAt

Result:

PASS

---

## Conclusion

The application successfully retrieves the currently authenticated user's profile using the JWT token without requiring the client to send an email or user ID.

---

**Status**

✅ Completed
