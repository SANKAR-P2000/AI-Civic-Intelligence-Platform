# Role-Based Authorization Verification

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Verify that each user role can access only the authorized APIs.

---

# Authorization Matrix

| Endpoint                        | CITIZEN | ADMIN | GOVERNMENT |
| ------------------------------- | ------- | ----- | ---------- |
| POST /api/complaints            | ✅      | ❌    | ❌         |
| GET /api/complaints/my          | ✅      | ❌    | ❌         |
| GET /api/complaints             | ❌      | ✅    | ✅         |
| PUT /api/complaints/{id}/status | ❌      | ✅    | ✅         |

---

# Verification Results

## Citizen

- Can create complaints.
- Can view own complaints.
- Cannot view all complaints.
- Cannot update complaint status.

---

## Admin

- Can view all complaints.
- Can update complaint status.
- Cannot create complaints.

---

## Government

- Can view all complaints.
- Can update complaint status.
- Cannot create complaints.

---

# Expected HTTP Status Codes

| Action          | Status           |
| --------------- | ---------------- |
| Authorized      | 200 OK           |
| Forbidden       | 403 Forbidden    |
| Unauthenticated | 401 Unauthorized |

---

# Conclusion

Role-based authorization is correctly enforced using Spring Security and `@PreAuthorize`.

---

# Status

Phase 14.15

STATUS

COMPLETED
