# Complaint Controller

## Objective

Expose REST APIs for Complaint Management.

---

## Endpoints

### POST /api/complaints

Creates a complaint.

Access

CITIZEN

---

### GET /api/complaints/my

Returns complaints created by the authenticated citizen.

Access

CITIZEN

---

### GET /api/complaints

Returns all complaints.

Access

- ADMIN
- GOVERNMENT

---

### PUT /api/complaints/{id}/status

Updates complaint status.

Access

- ADMIN
- GOVERNMENT

---

## Security

Uses:

@PreAuthorize

Role-based authorization protects all complaint APIs.

---

## Status

✅ Completed
