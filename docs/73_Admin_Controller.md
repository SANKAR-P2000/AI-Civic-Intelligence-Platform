# Admin Controller

## Objective

Create a dedicated REST controller for administrator-only endpoints.

---

## Controller

AdminController

---

## Base URL

```
/api/admin
```

---

## Endpoint

```
GET /api/admin/dashboard
```

---

## Authorization

```java
@PreAuthorize("hasRole('ADMIN')")
```

Only users with the ADMIN role can access this endpoint.

---

## Response

```
Welcome to the Admin Dashboard
```

---

## Benefits

- Separates administrator functionality.
- Improves project structure.
- Supports future admin APIs.
- Uses Spring Security role-based authorization.

---

## Status

✅ Completed
