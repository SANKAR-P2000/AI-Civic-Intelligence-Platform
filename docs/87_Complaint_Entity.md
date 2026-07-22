# Complaint Entity

## Objective

Create the Complaint JPA Entity that stores all civic complaints.

---

## Table

complaints

---

## Fields

- id
- title
- description
- category
- status
- location
- imageUrl
- citizen
- createdAt
- updatedAt

---

## Relationship

One User

↓

Many Complaints

---

## Features

- Automatic timestamps
- Default status (PENDING)
- Complaint category
- Complaint owner
- Future image support

---

## Benefits

- Central complaint storage.
- Supports complaint lifecycle.
- Maintains data consistency.
- Foundation for complaint APIs.

---

## Status

✅ Completed
