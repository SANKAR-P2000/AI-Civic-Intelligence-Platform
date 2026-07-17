# AI Civic Intelligence Platform (AICIP)

# Database Design

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Version  | 1.0                                    |
| Document | Database Design                        |
| Status   | Planning                               |

---

# 1. Introduction

This document defines the database entities required for the AI Civic Intelligence Platform (AICIP). It identifies the major entities, their purpose, and the relationships between them. This serves as the foundation for the relational database and future Spring Boot JPA entities.

---

# 2. Database Type

- Database Management System: MySQL
- Database Model: Relational Database
- ORM Framework: Spring Data JPA (Hibernate)

---

# 3. Entity Identification

The first version (V1.0) of AICIP contains the following core entities.

| Entity    | Purpose                                       |
| --------- | --------------------------------------------- |
| User      | Stores citizen and administrator accounts     |
| Complaint | Stores civic complaints submitted by citizens |
| Category  | Stores complaint categories                   |
| Status    | Stores complaint status values                |
| Priority  | Stores complaint priority levels              |

---

# 4. Entity Descriptions

## 4.1 User

Stores all registered users.

Examples:

- Citizen
- Administrator

Responsibilities:

- Authentication
- Profile Information
- Role Management

---

## 4.2 Complaint

Stores every complaint submitted by citizens.

Responsibilities:

- Complaint Details
- Complaint Description
- Complaint Location
- Complaint Image
- Complaint Tracking

---

## 4.3 Category

Defines complaint categories.

Examples:

- Garbage
- Pothole
- Water Leakage
- Street Light
- Sewage
- Road Damage

---

## 4.4 Status

Represents the current state of a complaint.

Available values:

- Submitted
- Under Review
- Assigned
- In Progress
- Resolved
- Rejected

---

## 4.5 Priority

Represents complaint urgency.

Available values:

- Low
- Medium
- High
- Critical

---

# 5. High-Level Relationships

One User can submit many Complaints.

One Complaint belongs to one Category.

One Complaint has one Status.

One Complaint has one Priority.

---

# 6. Database Normalization

The database will follow:

- First Normal Form (1NF)
- Second Normal Form (2NF)
- Third Normal Form (3NF)

This minimizes data redundancy and improves data integrity.

---

# 7. Version 1 Scope

Included:

- User
- Complaint
- Category
- Status
- Priority

Future versions may include:

- Department
- Notifications
- Feedback
- Audit Logs
- Complaint History
- AI Predictions

---

# 8. Summary

The database design focuses on a normalized relational structure that supports complaint management, authentication, tracking, and future AI-assisted features.

---

**End of Database Design**
