# File Upload Module Design

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 16 – File Upload & Image Management

---

# Objective

Allow citizens to upload complaint images instead of manually entering image URLs.

---

# Upload Flow

Citizen

↓

Multipart File Upload

↓

ComplaintController

↓

FileStorageService

↓

uploads/complaint-images

↓

Database

↓

Complaint Response

---

# Upload Directory

uploads/

└── complaint-images/

---

# Supported Formats

- jpg
- jpeg
- png
- webp

---

# Maximum File Size

10 MB

---

# Benefits

- Production-ready design
- Reusable service
- Easy migration to cloud storage
- Better user experience

---

# Future Enhancement

Cloud storage integration:

- AWS S3
- Azure Blob Storage
- Google Cloud Storage

---

# Status

Phase 16.1

Completed
