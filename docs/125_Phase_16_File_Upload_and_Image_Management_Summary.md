# Phase 16 – File Upload & Image Management Summary

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 16 – File Upload & Image Management

---

# Objective

The objective of Phase 16 is to provide citizens with the ability to upload complaint images securely and store them on the server. The uploaded images are linked to complaints and can be viewed through a public URL.

This phase introduces secure file storage, validation, static resource mapping, and image integration with the Complaint Management module.

---

# Features Implemented

## 1. File Upload API

Implemented a secure REST API to upload complaint images.

**Endpoint**

```
POST /api/files/upload
```

**Authentication**

- JWT Bearer Token Required

**Request Type**

- multipart/form-data

**Response**

Returns a unique filename generated using UUID.

Example

```
6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png
```

---

## 2. File Storage Service

Created a dedicated service responsible for handling file operations.

Responsibilities:

- Store uploaded images
- Generate UUID filenames
- Preserve original file extension
- Save files inside uploads directory
- Return stored filename

---

## 3. Upload Directory

Created local storage directory

```
uploads/
```

All uploaded complaint images are stored here.

---

## 4. UUID Filename Generation

To avoid filename conflicts, every uploaded file is renamed.

Example

Original

```
road.png
```

Stored As

```
6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png
```

Benefits

- Prevents duplicate filenames
- Improves security
- Avoids accidental overwrite

---

## 5. Image Validation

Implemented validation before storing files.

Allowed Formats

- JPG
- JPEG
- PNG

Rejected Formats

- PDF
- DOC
- DOCX
- ZIP
- EXE
- Other unsupported files

Validation also checks for empty uploads.

---

## 6. Complaint Image Integration

The Complaint module now stores uploaded image information.

Example

```json
{
  "title": "Broken Street Light",
  "imageUrl": "6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png"
}
```

---

## 7. Image URL Generation

While returning complaint details, the backend automatically converts the stored filename into a complete image URL.

Example

Stored

```
6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png
```

Returned

```
http://localhost:8080/uploads/6cf97fbf-2f2c-4679-b168-5d336bc15b6d.png
```

---

## 8. Static Resource Configuration

Spring Boot is configured to expose uploaded files.

URL Pattern

```
http://localhost:8080/uploads/{filename}
```

This allows uploaded complaint images to be viewed directly in a browser.

---

## 9. Security

The upload endpoint is protected using JWT authentication.

Authenticated users can:

- Upload complaint images
- Create complaints using uploaded images

Unauthenticated users cannot upload files.

---

## 10. Postman Testing

The following APIs were successfully tested.

| API                  | Result    |
| -------------------- | --------- |
| User Login           | ✅ Passed |
| Upload PNG           | ✅ Passed |
| Upload JPG           | ✅ Passed |
| Create Complaint     | ✅ Passed |
| Get My Complaints    | ✅ Passed |
| Track Complaint      | ✅ Passed |
| Dashboard Statistics | ✅ Passed |
| Dashboard Categories | ✅ Passed |
| Dashboard Status     | ✅ Passed |
| Static Image Access  | ✅ Passed |

---

# Project Structure

```
src
└── main
    ├── java
    │   └── com.sankar.aicip
    │       ├── controller
    │       │      FileUploadController.java
    │       │
    │       ├── service
    │       │      FileStorageService.java
    │       │      FileStorageServiceImpl.java
    │       │
    │       └── config
    │              WebConfig.java
    │
    └── resources

uploads/
```

---

# Security Workflow

```
Citizen Login
        │
        ▼
Receive JWT Token
        │
        ▼
Upload Image
        │
        ▼
Server Generates UUID Filename
        │
        ▼
Image Stored in uploads/
        │
        ▼
Filename Saved in Complaint
        │
        ▼
Complaint API Returns Full Image URL
        │
        ▼
Browser Displays Uploaded Image
```

---

# Technologies Used

- Java 25
- Spring Boot
- Spring MVC
- Spring Security
- JWT Authentication
- MultipartFile
- Java NIO Files API
- UUID
- MySQL
- Postman

---

# Learning Outcomes

After completing this phase, the project supports:

- Secure image uploads
- Multipart file handling
- UUID-based filename generation
- File validation
- Static resource serving
- Complaint image integration
- JWT-protected upload APIs
- Browser-accessible image URLs

---

# Phase Deliverables

- Secure File Upload API
- File Storage Service
- Upload Directory Configuration
- Image Validation
- UUID Filename Generation
- Complaint Image Integration
- Static Resource Configuration
- Browser Image Access
- Complete Postman Testing
- Technical Documentation

---

# Conclusion

Phase 16 successfully introduces a complete image management system into the AI Civic Intelligence Platform. Citizens can securely upload complaint images, associate them with complaints, and retrieve them through browser-accessible URLs. The implementation follows a modular architecture with proper validation, authentication, and storage practices, providing a solid foundation for future enhancements such as cloud storage, image compression, and multiple file attachments.

---

# Status

**Phase 16 – File Upload & Image Management**

✅ Completed Successfully
