# Complaint Service Interface

## Objective

Define the business operations for Complaint Management.

---

## Methods

### createComplaint()

Creates a new complaint submitted by a citizen.

---

### getMyComplaints()

Returns all complaints created by the authenticated citizen.

---

### getAllComplaints()

Returns all complaints in the system.

Accessible by:

- ADMIN
- GOVERNMENT

---

### updateComplaintStatus()

Updates the status of an existing complaint.

Possible Statuses

- PENDING
- UNDER_REVIEW
- IN_PROGRESS
- RESOLVED
- REJECTED

---

## Benefits

- Separates business logic from controllers.
- Supports layered architecture.
- Improves maintainability.
- Enables unit testing.

---

## Status

✅ Completed
