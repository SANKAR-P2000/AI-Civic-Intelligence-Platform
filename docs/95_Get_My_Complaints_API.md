# Get My Complaints API

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Allow an authenticated citizen to view only the complaints they have submitted.

The citizen is identified using the JWT token. No email or user ID is required in the request.

---

# Endpoint

```
GET /api/complaints/my
```

---

# Access

ROLE_CITIZEN

---

# Authentication

JWT Token Required

Authorization Header

```
Bearer <JWT_TOKEN>
```

---

# Request

No request body is required.

Example

```
GET http://localhost:8080/api/complaints/my
```

---

# Business Flow

```
Citizen Login
        │
        ▼
Receive JWT Token
        │
        ▼
GET /api/complaints/my
        │
        ▼
JWT Authentication
        │
        ▼
Extract Email from JWT
        │
        ▼
Find Logged-in Citizen
        │
        ▼
Retrieve Citizen Complaints
        │
        ▼
Convert Entity to DTO
        │
        ▼
Return Complaint List
```

---

# Successful Response

HTTP Status

```
200 OK
```

Example

```json
[
  {
    "id": 1,
    "title": "Large pothole on Main Road",
    "description": "A large pothole is causing accidents near the bus stop.",
    "category": "ROAD_DAMAGE",
    "status": "PENDING",
    "location": "Main Road, Chennai",
    "imageUrl": "https://example.com/pothole.jpg",
    "citizenName": "Anand Kumar",
    "citizenEmail": "anand@gmail.com",
    "createdAt": "2026-07-22T19:09:44.482047",
    "updatedAt": "2026-07-22T19:09:44.482047"
  }
]
```

---

# Database Query

```sql
SELECT * FROM complaints;
```

Verify

- Complaint exists.
- citizen_id belongs to logged-in user.
- Status is PENDING.

---

# Security Verification

Only authenticated users having

```
ROLE_CITIZEN
```

can access this endpoint.

The endpoint returns only the complaints created by the currently authenticated citizen.

Complaints created by other users are not returned.

---

# Verification Checklist

- JWT authentication successful.
- Response status is 200 OK.
- Response is a JSON array.
- Only logged-in citizen's complaints are returned.
- Complaint details match the database.

---

# Learning Outcomes

After completing this API, I learned:

- How to retrieve the authenticated user from Spring Security.
- How to filter records based on the logged-in user.
- How to return DTOs instead of entities.
- How to secure APIs using JWT Authentication.

---

# Status

```
Phase 14.12

Get My Complaints API

STATUS

COMPLETED
```
