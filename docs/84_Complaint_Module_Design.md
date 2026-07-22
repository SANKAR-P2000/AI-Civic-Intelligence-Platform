# Complaint Module Design

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Design the Complaint Management Module, which serves as the core business functionality of the AI Civic Intelligence Platform.

This module enables:

- Citizens to submit civic complaints.
- Government officials to review complaints.
- Administrators to monitor and manage all complaints.
- Secure access through JWT Authentication and Role-Based Authorization.

---

# Complaint Management Workflow

```
Citizen
    │
    ▼
Create Complaint
    │
    ▼
Complaint Saved
(Status = PENDING)
    │
    ▼
Government Reviews
    │
    ├──────── UNDER_REVIEW
    │
    ├──────── IN_PROGRESS
    │
    ├──────── RESOLVED
    │
    └──────── REJECTED
```

---

# Complaint Entity

Each complaint contains the following information.

| Field       | Type              | Description                             |
| ----------- | ----------------- | --------------------------------------- |
| id          | Long              | Complaint ID                            |
| title       | String            | Complaint title                         |
| description | String            | Detailed complaint description          |
| category    | ComplaintCategory | Complaint category                      |
| status      | ComplaintStatus   | Current complaint status                |
| location    | String            | Complaint location                      |
| imageUrl    | String            | Uploaded image URL (future enhancement) |
| citizen     | User              | Complaint owner                         |
| createdAt   | LocalDateTime     | Complaint creation timestamp            |
| updatedAt   | LocalDateTime     | Last modification timestamp             |

---

# Complaint Categories

The system classifies complaints into the following categories.

- ROAD_DAMAGE
- STREET_LIGHT
- GARBAGE
- WATER_SUPPLY
- DRAINAGE
- TRAFFIC
- PUBLIC_TRANSPORT
- ENVIRONMENT
- OTHER

---

# Complaint Status Lifecycle

```
PENDING
      │
      ▼
UNDER_REVIEW
      │
      ▼
IN_PROGRESS
      │
      ▼
RESOLVED
```

Alternative Flow

```
PENDING
      │
      ▼
REJECTED
```

---

# Database Relationship

```
             User
         (Citizen)
             │
             │ 1
             │
             ▼
        Complaint
             ▲
             │
             │ Many
```

One User can create multiple complaints.

Each Complaint belongs to exactly one User.

---

# Package Structure

```
com.sankar.aicip
│
├── controller
│      └── ComplaintController.java
│
├── dto
│      ├── request
│      │      └── CreateComplaintRequest.java
│      │
│      └── response
│             └── ComplaintResponse.java
│
├── entity
│      └── Complaint.java
│
├── enums
│      ├── ComplaintCategory.java
│      └── ComplaintStatus.java
│
├── repository
│      └── ComplaintRepository.java
│
├── service
│      ├── ComplaintService.java
│      └── impl
│             └── ComplaintServiceImpl.java
```

---

# Module Features

- Complaint Registration
- Complaint Tracking
- Complaint Status Management
- Complaint Categorization
- Citizen Complaint History
- Government Complaint Dashboard
- Administrator Complaint Monitoring

---

# Future Enhancements

This design supports future features including:

- AI Complaint Classification
- Image Upload
- GPS Location Integration
- Email Notifications
- Complaint Analytics Dashboard
- Power BI Integration
- Predictive Analytics
- Smart Complaint Prioritization

---

# Learning Outcomes

After completing this phase, I learned:

- How to design a business module before implementation.
- How to model database relationships.
- How to identify entities and workflows.
- How to prepare scalable application architecture.

---

# Status

```
Phase 14.1

Complaint Module Design

STATUS

COMPLETED
```
