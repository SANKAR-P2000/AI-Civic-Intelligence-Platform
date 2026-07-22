# Update Complaint Status API Examples

## Project

AI Civic Intelligence Platform (AICIP)

---

# Endpoint

```
PUT /api/complaints/{id}/status
```

This endpoint updates the status of an existing complaint.

---

# URL Format

```
http://localhost:8080/api/complaints/{id}/status?status={STATUS}
```

Where:

- `{id}` = Complaint ID
- `{STATUS}` = New Complaint Status

---

# Path Variable

```
{id}
```

Example

```
1
```

Result

```
http://localhost:8080/api/complaints/1/status
```

---

# Query Parameter

```
status
```

Possible values

- PENDING
- UNDER_REVIEW
- IN_PROGRESS
- RESOLVED
- REJECTED

---

# Example 1

Move complaint to UNDER_REVIEW

```
PUT http://localhost:8080/api/complaints/1/status?status=UNDER_REVIEW
```

Expected Response

```
200 OK
```

---

# Example 2

Move complaint to IN_PROGRESS

```
PUT http://localhost:8080/api/complaints/1/status?status=IN_PROGRESS
```

Expected Response

```
200 OK
```

---

# Example 3

Move complaint to RESOLVED

```
PUT http://localhost:8080/api/complaints/1/status?status=RESOLVED
```

Expected Response

```
200 OK
```

---

# Example 4

Reject Complaint

```
PUT http://localhost:8080/api/complaints/1/status?status=REJECTED
```

Expected Response

```
200 OK
```

---

# Authentication

JWT Token Required

Authorization

```
Bearer <ADMIN_OR_GOVERNMENT_JWT>
```

---

# Roles Allowed

- ADMIN
- GOVERNMENT

---

# Roles Not Allowed

- CITIZEN

Expected

```
403 Forbidden
```

---

# Example Response

```json
{
  "id": 1,
  "title": "Large pothole on Main Road",
  "category": "ROAD_DAMAGE",
  "status": "RESOLVED",
  "location": "Main Road, Chennai",
  "citizenName": "Anand Kumar",
  "citizenEmail": "anand@gmail.com"
}
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

# Database Verification

```sql
SELECT id, title, status
FROM complaints;
```

Example Result

| id  | title                      | status   |
| --- | -------------------------- | -------- |
| 1   | Large pothole on Main Road | RESOLVED |

---

# Summary

The Update Complaint Status API allows authorized users to manage the lifecycle of complaints by changing their status through a simple PUT request using the complaint ID and the desired status.
