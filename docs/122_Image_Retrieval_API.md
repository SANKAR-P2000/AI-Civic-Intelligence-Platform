# Image Retrieval API

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 16 – File Upload & Image Management

---

# Objective

Return complete image URLs in complaint responses.

---

# Before

```json
{
  "imageUrl": "6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png"
}
```

---

# After

```json
{
  "imageUrl": "http://localhost:8080/uploads/6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png"
}
```

---

# Benefits

- Frontend can directly display images.
- No URL construction required in the client.
- Consistent API responses.
- Better user experience.

---

# APIs Updated

- POST /api/complaints
- GET /api/complaints
- GET /api/complaints/my
- GET /api/complaints/track/{id}

---

# Status

Phase 16.7

Completed
