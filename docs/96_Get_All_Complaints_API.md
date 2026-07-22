# Get All Complaints API

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Allow ADMIN and GOVERNMENT users to retrieve all complaints in the system.

---

# Endpoint

GET /api/complaints

---

# Access

- ADMIN
- GOVERNMENT

---

# Authentication

JWT Token Required

Authorization

Bearer <JWT_TOKEN>

---

# Response

Returns a list of all complaints with:

- Complaint ID
- Title
- Description
- Category
- Status
- Citizen Information
- Created Timestamp
- Updated Timestamp

---

# Security

ADMIN → Allowed

GOVERNMENT → Allowed

CITIZEN → Forbidden (403)

---

# Database Verification

```sql
SELECT id, title, category, status, citizen_id
FROM complaints;
```

---

# Verification Checklist

- JWT authentication successful.
- Response status is 200 OK.
- Returns all complaints.
- ADMIN access verified.
- GOVERNMENT access verified.
- CITIZEN access denied.

---

# Status

```
Phase 14.13

Get All Complaints API

STATUS

COMPLETED
```
