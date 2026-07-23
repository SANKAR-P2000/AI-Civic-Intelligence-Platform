# Upload Directory Configuration

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 16 – File Upload & Image Management

---

# Objective

Configure Spring Boot for multipart file uploads and define the upload directory.

---

# application.properties

```properties
file.upload-dir=uploads/complaint-images

spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

---

# Upload Directory

uploads/

└── complaint-images/

---

# .gitignore

```gitignore
uploads/
```

---

# Benefits

- Supports multipart file uploads
- Prevents large uploads
- Separates uploaded files from source code
- Prevents uploaded images from being committed to GitHub

---

# Status

Phase 16.2

Completed
