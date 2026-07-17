# AI Civic Intelligence Platform (AICIP)

# API Design

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Version  | 1.0                                    |
| Document | API Design                             |
| Status   | Planning                               |

---

# 1. Introduction

This document defines the REST API endpoints for the AI Civic Intelligence Platform (AICIP). These APIs enable communication between the React frontend and the Spring Boot backend.

The API follows RESTful principles using JSON for request and response data.

---

# 2. Base URL

```
/api/v1
```

---

# 3. Authentication APIs

| Method | Endpoint       | Description                      |
| ------ | -------------- | -------------------------------- |
| POST   | /auth/register | Register a new user              |
| POST   | /auth/login    | Authenticate user and return JWT |
| GET    | /auth/profile  | Get logged-in user profile       |
| PUT    | /auth/profile  | Update logged-in user profile    |

---

# 4. Complaint APIs

| Method | Endpoint                  | Description            |
| ------ | ------------------------- | ---------------------- |
| POST   | /complaints               | Create a complaint     |
| GET    | /complaints               | Get all complaints     |
| GET    | /complaints/{id}          | Get complaint by ID    |
| PUT    | /complaints/{id}          | Update complaint       |
| DELETE | /complaints/{id}          | Delete complaint       |
| GET    | /complaints/user/{userId} | Get complaints by user |

---

# 5. Category APIs

| Method | Endpoint         | Description        |
| ------ | ---------------- | ------------------ |
| GET    | /categories      | Get all categories |
| POST   | /categories      | Add category       |
| PUT    | /categories/{id} | Update category    |
| DELETE | /categories/{id} | Delete category    |

---

# 6. Status APIs

| Method | Endpoint       | Description      |
| ------ | -------------- | ---------------- |
| GET    | /statuses      | Get all statuses |
| POST   | /statuses      | Add status       |
| PUT    | /statuses/{id} | Update status    |
| DELETE | /statuses/{id} | Delete status    |

---

# 7. Priority APIs

| Method | Endpoint         | Description        |
| ------ | ---------------- | ------------------ |
| GET    | /priorities      | Get all priorities |
| POST   | /priorities      | Add priority       |
| PUT    | /priorities/{id} | Update priority    |
| DELETE | /priorities/{id} | Delete priority    |

---

# 8. Dashboard APIs

| Method | Endpoint         | Description                  |
| ------ | ---------------- | ---------------------------- |
| GET    | /dashboard/admin | Admin dashboard statistics   |
| GET    | /dashboard/user  | Citizen dashboard statistics |

---

# 9. Future APIs

Future versions may include:

- Notification APIs
- AI Prediction APIs
- Feedback APIs
- File Storage APIs
- Audit Log APIs

---

# 10. Response Format

## Success Response

```json
{
  "success": true,
  "message": "Operation completed successfully",
  "data": {}
}
```

## Error Response

```json
{
  "success": false,
  "message": "Validation failed",
  "errors": []
}
```

---

# 11. HTTP Status Codes

| Code | Meaning               |
| ---- | --------------------- |
| 200  | OK                    |
| 201  | Created               |
| 400  | Bad Request           |
| 401  | Unauthorized          |
| 403  | Forbidden             |
| 404  | Not Found             |
| 500  | Internal Server Error |

---

# 12. Summary

The REST API is designed using resource-oriented endpoints, standard HTTP methods, and consistent JSON responses. It provides a scalable interface between the frontend and backend while supporting future feature expansion.

---

**End of API Design**
