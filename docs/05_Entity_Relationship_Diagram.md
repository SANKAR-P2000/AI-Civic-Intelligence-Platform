# AI Civic Intelligence Platform (AICIP)

# Entity Relationship Diagram (ERD)

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Version  | 1.0                                    |
| Document | Entity Relationship Diagram (ERD)      |
| Status   | Planning                               |

---

# 1. Purpose

This document defines the relationships between the database entities of the AI Civic Intelligence Platform.

The ERD acts as the blueprint for:

- MySQL Database
- Spring Boot JPA Entities
- Repository Layer
- REST APIs

---

# 2. Entities

The system contains the following entities.

1. User
2. Complaint
3. Category
4. Status
5. Priority

---

# 3. Relationships

## User → Complaint

Relationship

One-to-Many (1:N)

Meaning

One user can submit many complaints.

One complaint belongs to exactly one user.

---

## Category → Complaint

Relationship

One-to-Many (1:N)

Meaning

One category can contain many complaints.

One complaint belongs to one category.

---

## Status → Complaint

Relationship

One-to-Many (1:N)

Meaning

One status can be assigned to many complaints.

One complaint has only one current status.

---

## Priority → Complaint

Relationship

One-to-Many (1:N)

Meaning

One priority level can be assigned to many complaints.

One complaint has only one priority level.

---

# 4. Relationship Summary

User (1)

↓

Complaint (Many)

Complaint (Many)

↑

Category (1)

Complaint (Many)

↑

Status (1)

Complaint (Many)

↑

Priority (1)

---

# 5. Primary Keys

| Entity    | Primary Key  |
| --------- | ------------ |
| User      | user_id      |
| Complaint | complaint_id |
| Category  | category_id  |
| Status    | status_id    |
| Priority  | priority_id  |

---

# 6. Foreign Keys

Complaint table contains:

- user_id
- category_id
- status_id
- priority_id

---

# 7. Cardinality

| Parent   | Child     | Type        |
| -------- | --------- | ----------- |
| User     | Complaint | One-to-Many |
| Category | Complaint | One-to-Many |
| Status   | Complaint | One-to-Many |
| Priority | Complaint | One-to-Many |

---

# 8. Summary

The database uses a normalized relational model where the Complaint entity acts as the central table connecting users, categories, statuses, and priorities.

This design supports efficient querying, easy maintenance, and scalable application development.

---

**End of ERD Document**
