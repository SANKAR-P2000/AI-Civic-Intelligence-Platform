# Phase 15 – Postman Testing

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 15 – Public Complaint Tracking & Dashboard

---

# Objective

Verify all APIs developed during Phase 15 using Postman and ensure:

- Authentication works correctly
- Authorization rules are enforced
- Complaint APIs function correctly
- Dashboard APIs return accurate analytics
- MySQL data remains consistent

---

# Authentication Module Testing

## Register User

Endpoint

POST /api/users/register

Result

✅ User registered successfully

---

## Login

Endpoint

POST /api/users/login

Result

✅ JWT Access Token generated

✅ Refresh Token generated

---

## Refresh Token

Endpoint

POST /api/auth/refresh

Result

✅ New Access Token generated successfully

---

## Logout

Endpoint

POST /api/auth/logout

Result

✅ Refresh token removed from database

---

# Complaint Module Testing

## Create Complaint

Endpoint

POST /api/complaints

Result

✅ Complaint created successfully

Default Status:

PENDING

---

## My Complaints

Endpoint

GET /api/complaints/my

Result

✅ Returns logged-in citizen complaints

---

## All Complaints

Endpoint

GET /api/complaints

Authorized Roles

- ADMIN
- GOVERNMENT

Result

✅ Returns all complaints

---

## Track Complaint

Endpoint

GET /api/complaints/track/{id}

Result

✅ Complaint retrieved successfully

---

## Update Complaint Status

Endpoint

PUT /api/complaints/{id}/status

Verified Status Updates

- PENDING
- UNDER_REVIEW
- IN_PROGRESS
- RESOLVED

Result

✅ Status updated successfully

---

# Dashboard Module Testing

## Dashboard Statistics

Endpoint

GET /api/dashboard/stats

Verified

- Total Complaints
- Pending
- Under Review
- In Progress
- Resolved
- Rejected

Result

✅ Correct statistics returned

---

## Category Analytics

Endpoint

GET /api/dashboard/categories

Example

ROAD_DAMAGE → 1

Result

✅ Category aggregation working

---

## Status Analytics

Endpoint

GET /api/dashboard/status

Example

RESOLVED → 1

Result

✅ Status aggregation working

---

# Authorization Testing

## ADMIN

Dashboard APIs

✅ Allowed

Complaint Status Update

✅ Allowed

---

## GOVERNMENT

Dashboard APIs

✅ Allowed

Complaint Status Update

✅ Allowed

---

## CITIZEN

Dashboard APIs

❌ Forbidden (403)

Complaint Status Update

❌ Forbidden (403)

Complaint Tracking

✅ Allowed

Create Complaint

✅ Allowed

My Complaints

✅ Allowed

---

# Database Verification

Verified Tables

## users

- User records stored correctly

---

## complaints

Verified

- Complaint Created
- Status Updated
- Created Time
- Updated Time

---

## refresh_tokens

Verified

- Token created after login
- Token deleted after logout

---

# Technologies Tested

- Spring Boot
- Spring Security
- JWT Authentication
- Refresh Tokens
- Spring Data JPA
- MySQL
- Postman

---

# Overall Result

| Module               | Status |
| -------------------- | ------ |
| Authentication       | ✅     |
| JWT Security         | ✅     |
| Refresh Tokens       | ✅     |
| Authorization        | ✅     |
| Complaint Management | ✅     |
| Complaint Tracking   | ✅     |
| Dashboard Statistics | ✅     |
| Dashboard Analytics  | ✅     |
| Database Integration | ✅     |

---

# Conclusion

Phase 15 has been successfully implemented, tested, and verified.

All APIs are functioning correctly and are ready for production-level integration with the frontend.

---

# Status

Phase 15.10

Completed
