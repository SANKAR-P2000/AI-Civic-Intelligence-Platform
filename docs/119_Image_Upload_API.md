# Image Upload API

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 16 – File Upload & Image Management

---

# Objective

Allow authenticated users to upload complaint images.

---

# Endpoint

POST /api/files/upload

---

# Authentication

JWT Bearer Token Required.

---

# Request Type

multipart/form-data

Key

file

Type

File

---

# Response

Returns the generated filename.

Example

550e8400-e29b-41d4-a716-446655440000.jpg

---

# Storage Location

uploads/

└── complaint-images/

---

# Benefits

- Real file upload
- UUID filename generation
- Secure upload
- Reusable for future modules

---

# Status

Phase 16.4

Completed
