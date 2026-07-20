# AI Civic Intelligence Platform (AICIP)

# Postman Setup

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Document | Postman Setup                          |
| Version  | 1.0                                    |
| Status   | In Progress                            |

---

# 1. What is Postman?

Postman is an API development and testing tool.

It allows developers to send HTTP requests to a backend application without creating a frontend application.

It is one of the most widely used tools for testing REST APIs.

---

# 2. Why Do We Need Postman?

During backend development, the frontend application is usually not ready.

Instead of waiting for the frontend, developers use Postman to:

- Test APIs
- Verify request data
- Verify response data
- Debug backend applications

---

# 3. Purpose of Postman

Postman is used to communicate directly with backend APIs.

Example:

```
Client (Postman)
        ↓
Spring Boot REST API
        ↓
Service Layer
        ↓
Repository
        ↓
MySQL Database
```

---

# 4. Features

- Send HTTP Requests
- Test REST APIs
- View JSON Responses
- Add Headers
- Send Request Body
- Authentication Testing
- File Upload Testing
- API Collections
- Environment Variables

---

# 5. HTTP Methods Used

| Method | Purpose              |
| ------ | -------------------- |
| GET    | Read data            |
| POST   | Create data          |
| PUT    | Update complete data |
| PATCH  | Update partial data  |
| DELETE | Delete data          |

---

# 6. Data Formats

Postman supports:

- JSON
- XML
- Form Data
- Binary
- Raw Text

Most Spring Boot applications use JSON.

---

# 7. Why We Are Using Postman in AICIP

We will use Postman to test:

- User Registration
- User Login
- Complaint Creation
- Complaint Update
- Complaint Status
- Admin APIs
- Dashboard APIs
- JWT Authentication
- File Upload APIs

---

# 8. Benefits

- Easy to use
- No frontend required
- Saves development time
- Industry standard
- Supports automation
- Helps debugging APIs

---

# 9. Learning Outcome

After completing this project, I will be able to:

- Create API requests
- Test REST APIs
- Read JSON responses
- Debug backend APIs
- Work with authentication
- Test file uploads
- Organize API collections

---

**End of Postman Setup**
