# AI Civic Intelligence Platform (AICIP)

# DTO Architecture

---

## Purpose

DTO (Data Transfer Object) is used to transfer data between the client and the server.

DTOs prevent exposing entity classes directly through REST APIs.

---

## Project Structure

```
dto
│
├── request
└── response
```

---

## Request DTO

Receives data from the client.

Example:

- Registration Request
- Login Request
- Complaint Request

---

## Response DTO

Returns data to the client.

Example:

- User Details
- Complaint Details
- Dashboard Data

---

## Benefits

- Better Security
- Better API Design
- Loose Coupling
- Easier Maintenance
- Independent of Entity Structure

---

**End of DTO Architecture**
