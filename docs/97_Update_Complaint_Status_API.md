# Update Complaint Status API

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Allow ADMIN and GOVERNMENT users to update the status of an existing complaint.

This API manages the complaint lifecycle from submission to resolution.

---

# Endpoint

```
PUT /api/complaints/{id}/status
```

Example

```
PUT /api/complaints/1/status?status=UNDER_REVIEW
```

---

# Access

- ADMIN
- GOVERNMENT

---

# Authentication

JWT Token Required

Authorization Header

```
Bearer <JWT_TOKEN>
```

---

# Request

Method

```
PUT
```

Path Variable

```
id
```

Query Parameter

```
status
```

Example

```
PUT /api/complaints/1/status?status=UNDER_REVIEW
```

---

# Complaint Lifecycle

```
PENDING
    │
    ▼
UNDER_REVIEW
    │
    ▼
IN_PROGRESS
    │
    ▼
RESOLVED
```

Alternative

```
PENDING
    │
    ▼
REJECTED
```

---

# Successful Response

HTTP Status

```
200 OK
```

Example

```json
{
  "id": 1,
  "title": "Large pothole on Main Road",
  "description": "A large pothole is causing accidents near the bus stop.",
  "category": "ROAD_DAMAGE",
  "status": "UNDER_REVIEW",
  "location": "Main Road, Chennai",
  "imageUrl": "https://example.com/pothole.jpg",
  "citizenName": "Anand Kumar",
  "citizenEmail": "anand@gmail.com",
  "createdAt": "2026-07-22T19:09:44.482047",
  "updatedAt": "2026-07-22T19:26:00.0618859"
}
```

---

# Database Verification

```sql
SELECT
    id,
    title,
    status
FROM complaints;
```

Expected

| id  | title                      | status       |
| --- | -------------------------- | ------------ |
| 1   | Large pothole on Main Road | UNDER_REVIEW |

After additional updates

| id  | title                      | status      |
| --- | -------------------------- | ----------- |
| 1   | Large pothole on Main Road | IN_PROGRESS |

Finally

| id  | title                      | status   |
| --- | -------------------------- | -------- |
| 1   | Large pothole on Main Road | RESOLVED |

---

# Security

Allowed

- ADMIN
- GOVERNMENT

Denied

- CITIZEN

Expected Response

```
403 Forbidden
```

---

# Verification Checklist

- JWT authentication successful.
- ADMIN can update complaint status.
- GOVERNMENT can update complaint status.
- CITIZEN cannot update complaint status.
- Complaint status changes successfully.
- Database reflects the updated status.
- updatedAt timestamp changes after every update.

---

# Learning Outcomes

After completing this API, I learned:

- How to update an existing entity using Spring Data JPA.
- How to use Path Variables and Request Parameters together.
- How to implement role-based authorization with @PreAuthorize.
- How to manage a complaint lifecycle using enums.
- How to verify updates through Postman and MySQL.

---

# Status

```
Phase 14.14

Update Complaint Status API

STATUS

COMPLETED
```
