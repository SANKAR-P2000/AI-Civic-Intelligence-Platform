# Complaint Service Implementation

## Objective

Implement the business logic for Complaint Management.

---

## Responsibilities

- Create Complaint
- Retrieve Citizen Complaints
- Retrieve All Complaints
- Update Complaint Status
- Convert Entity to DTO

---

## Methods

### createComplaint()

Creates a complaint and associates it with the authenticated citizen.

---

### getMyComplaints()

Returns all complaints submitted by the authenticated citizen.

---

### getAllComplaints()

Returns every complaint in the system.

---

### updateComplaintStatus()

Updates the status of an existing complaint.

---

## Internal Mapper

mapToResponse()

Converts Complaint Entity into ComplaintResponse DTO.

---

## Benefits

- Centralized business logic.
- Clean separation of concerns.
- Reusable mapping logic.
- Secure retrieval using authenticated user.

---

## Status

✅ Completed
