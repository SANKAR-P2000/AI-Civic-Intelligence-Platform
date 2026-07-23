# Static Resource Configuration

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 16 – File Upload & Image Management

---

# Objective

Expose uploaded images as static resources.

---

# Configuration

Class

WebConfig

Implements

WebMvcConfigurer

---

# URL Mapping

```
/uploads/**
```

↓

```
uploads/complaint-images/
```

---

# Example

Stored File

```
6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png
```

Accessible Via

```
http://localhost:8080/uploads/6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png
```

---

# Benefits

- Images accessible through HTTP
- Frontend can display uploaded images
- No controller required for image retrieval
- Simple and efficient development setup

---

# Technologies

- Spring MVC
- WebMvcConfigurer
- ResourceHandlerRegistry

---

# Status

Phase 16.6

Completed
