# AI Civic Intelligence Platform (AICIP)

# Database Schema Design

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Version  | 1.0                                    |
| Document | Database Schema Design                 |
| Status   | Planning                               |

---

# 1. Introduction

This document defines the database schema for the AI Civic Intelligence Platform (AICIP). It includes the database tables, their purpose, primary keys, foreign keys, and relationships.

The schema is designed according to relational database principles and will be implemented in MySQL.

---

# 2. Database Name

```
aicip_db
```

---

# 3. Tables

Version 1.0 contains the following tables:

| Table      | Purpose                          |
| ---------- | -------------------------------- |
| users      | Stores user information          |
| complaints | Stores complaint details         |
| categories | Stores complaint categories      |
| statuses   | Stores complaint status values   |
| priorities | Stores complaint priority values |

---

# 4. Table Overview

## 4.1 users

Purpose:

Stores registered users including citizens and administrators.

Primary Key:

- user_id

---

## 4.2 complaints

Purpose:

Stores complaints submitted by citizens.

Primary Key:

- complaint_id

Foreign Keys:

- user_id
- category_id
- status_id
- priority_id

---

## 4.3 categories

Purpose:

Stores available complaint categories.

Primary Key:

- category_id

---

## 4.4 statuses

Purpose:

Stores complaint status values.

Primary Key:

- status_id

---

## 4.5 priorities

Purpose:

Stores complaint priority values.

Primary Key:

- priority_id

---

# 5. Relationships

users (1)
↓

complaints (Many)

categories (1)
↓

complaints (Many)

statuses (1)
↓

complaints (Many)

priorities (1)
↓

complaints (Many)

---

# 6. Naming Convention

Tables

- Use lowercase
- Use plural names

Examples

- users
- complaints
- categories

Columns

- Use snake_case

Examples

- user_id
- complaint_title
- created_at

Primary Keys

- table_name_id format

Examples

- user_id
- complaint_id
- category_id

---

# 7. Future Tables

The following tables may be added in later versions:

- departments
- notifications
- feedback
- audit_logs
- complaint_history
- ai_predictions

---

# 8. Summary

The AICIP database schema consists of five core tables that provide a normalized and scalable foundation for complaint management. Additional tables can be introduced in future versions without affecting the existing schema.

---

**End of Database Schema Design**
