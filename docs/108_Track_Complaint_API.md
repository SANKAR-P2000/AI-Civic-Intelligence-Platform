# Track Complaint API

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Allow authenticated users to retrieve complaint details using the complaint ID.

---

# Endpoint

GET /api/complaints/track/{id}

---

# Authentication

JWT Bearer Token Required.

---

# Path Variable

id

Example

GET /api/complaints/track/1

---

# Successful Response

HTTP 200 OK

Returns:

- Complaint ID
- Title
- Description
- Category
- Status
- Location
- Citizen Name
- Citizen Email
- Created Time
- Updated Time

---

# Error Responses

401 Unauthorized

- Missing JWT
- Invalid JWT

404 Not Found

- Complaint ID does not exist

---

# Purpose

Allows authenticated users to track complaint progress using the complaint ID.

---

# Status

Phase 15.8

Completed
