# Protected Profile API

## Objective

Create the first JWT-protected REST endpoint.

---

## Endpoint

GET /api/users/profile

---

## Description

This endpoint is accessible only to authenticated users.

Initially it returns a simple success message.

---

## Success Response

HTTP 200 OK

```text
Authenticated User Profile
```

---

## Security

Authentication Required

JWT Token Required

---

## Testing

Without JWT

Expected:

401 Unauthorized

With JWT

Expected:

200 OK

---

## Future Enhancement

This endpoint will later return the currently authenticated user's profile information.

---

**End of Protected Profile API**
