# Image Validation

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 16 – File Upload & Image Management

---

# Objective

Validate uploaded files before storing them on the server.

The system accepts only supported image formats and prevents invalid or oversized uploads.

---

# Validation Rules

## 1. Empty File Validation

If no file is selected, the upload is rejected.

Example Error

```
Please select a file.
```

---

## 2. Supported File Types

Allowed formats:

- JPG
- JPEG
- PNG

Rejected formats:

- PDF
- ZIP
- EXE
- DOCX
- XLSX
- Any non-image file

Example Error

```
Only JPG and PNG images are allowed.
```

---

## 3. Maximum File Size

Maximum upload size:

```
5 MB
```

Files larger than 5 MB are rejected.

Example Error

```
Maximum upload size is 5 MB.
```

---

# Validation Flow

```
MultipartFile

      │

      ▼

Is Empty?

      │

      ├── Yes → Reject

      ▼

Check Content Type

      │

      ├── Invalid → Reject

      ▼

Check File Size

      │

      ├── Too Large → Reject

      ▼

Generate UUID

      ▼

Store File

      ▼

Return Stored Filename
```

---

# Benefits

- Prevents invalid uploads.
- Protects the server from unsupported file types.
- Limits storage usage.
- Improves application security.
- Provides a better user experience.

---

# Test Cases

| Test Case     | Expected Result                        |
| ------------- | -------------------------------------- |
| PNG Upload    | Success                                |
| JPG Upload    | Success                                |
| Empty Request | "Please select a file."                |
| PDF Upload    | "Only JPG and PNG images are allowed." |
| File > 5 MB   | "Maximum upload size is 5 MB."         |

---

# Technologies Used

- Spring Boot
- MultipartFile
- Java NIO
- UUID
- Spring MVC

---

# Future Improvements

- Return HTTP 400 (Bad Request) instead of RuntimeException.
- Add virus scanning.
- Validate image dimensions.
- Compress uploaded images automatically.

---

# Status

Phase 16.8

Completed
