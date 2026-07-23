# Complaint Image Integration

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 16 – File Upload & Image Management

---

# Objective

Integrate uploaded complaint images with the complaint creation process.

Instead of storing an external image URL, the system stores the filename of the uploaded image.

---

# Workflow

Step 1

Upload an image.

Endpoint

POST /api/files/upload

Response

```
a596dbf6-16c8-4734-8a10-7476da037c8d.png
```

---

Step 2

Create a complaint using the returned filename.

Example Request

```json
{
  "title": "Broken Street Light",
  "description": "Street light is not working.",
  "category": "STREET_LIGHT",
  "location": "Anna Nagar, Chennai",
  "imageUrl": "a596dbf6-16c8-4734-8a10-7476da037c8d.png"
}
```

---

# Database Storage

The `image_url` column stores only the generated filename.

Example

| id  | title               | image_url                                |
| --- | ------------------- | ---------------------------------------- |
| 2   | Broken Street Light | a596dbf6-16c8-4734-8a10-7476da037c8d.png |

---

# Benefits

- No dependency on external image hosting.
- Smaller database size.
- Images remain stored on the server.
- UUID filenames prevent collisions.
- Easy migration to cloud storage in the future.

---

# Request Flow

Citizen

↓

Upload Image

↓

POST /api/files/upload

↓

Receive Generated Filename

↓

POST /api/complaints

↓

Filename Stored in Database

↓

Complaint Created Successfully

---

# Technologies Used

- Spring Boot
- Spring MVC Multipart Support
- Spring Security
- UUID
- Java NIO Files API
- MySQL

---

# Learning Outcomes

After completing this phase, you understand:

- Multipart file uploads.
- File storage using Java NIO.
- UUID-based filename generation.
- Linking uploaded files with database records.
- Separating file storage from business data.

---

# Status

Phase 16.5

Completed
