# ComplaintResponse DTO

## Objective

Create a Data Transfer Object (DTO) used to return complaint information to clients.

---

## Fields

- id
- title
- description
- category
- status
- location
- imageUrl
- citizenName
- citizenEmail
- createdAt
- updatedAt

---

## Purpose

- Return complaint details.
- Avoid exposing the Complaint entity directly.
- Standardize API responses.

---

## Benefits

- Better API security.
- Clear response structure.
- Supports future API evolution.
- Decouples persistence from presentation.

---

## Status

✅ Completed