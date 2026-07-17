# AI Civic Intelligence Platform (AICIP)

# Table Structure Design

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Version  | 1.0                                    |
| Document | Table Structure Design                 |
| Status   | Planning                               |

---

# 1. Introduction

This document defines the detailed structure of each database table used in the AI Civic Intelligence Platform (AICIP). It specifies the columns, data types, constraints, and default values that will be implemented in MySQL.

---

# 2. Table: users

| Column       | Data Type    | Constraints                 |
| ------------ | ------------ | --------------------------- |
| user_id      | BIGINT       | Primary Key, Auto Increment |
| full_name    | VARCHAR(100) | NOT NULL                    |
| email        | VARCHAR(150) | UNIQUE, NOT NULL            |
| password     | VARCHAR(255) | NOT NULL                    |
| phone_number | VARCHAR(15)  | NOT NULL                    |
| role         | VARCHAR(20)  | NOT NULL                    |
| created_at   | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   |
| updated_at   | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   |

---

# 3. Table: categories

| Column        | Data Type    | Constraints                 |
| ------------- | ------------ | --------------------------- |
| category_id   | BIGINT       | Primary Key, Auto Increment |
| category_name | VARCHAR(100) | UNIQUE, NOT NULL            |
| description   | VARCHAR(255) | NULL                        |

---

# 4. Table: statuses

| Column      | Data Type   | Constraints                 |
| ----------- | ----------- | --------------------------- |
| status_id   | BIGINT      | Primary Key, Auto Increment |
| status_name | VARCHAR(50) | UNIQUE, NOT NULL            |

---

# 5. Table: priorities

| Column        | Data Type   | Constraints                 |
| ------------- | ----------- | --------------------------- |
| priority_id   | BIGINT      | Primary Key, Auto Increment |
| priority_name | VARCHAR(50) | UNIQUE, NOT NULL            |

---

# 6. Table: complaints

| Column       | Data Type    | Constraints                 |
| ------------ | ------------ | --------------------------- |
| complaint_id | BIGINT       | Primary Key, Auto Increment |
| title        | VARCHAR(150) | NOT NULL                    |
| description  | TEXT         | NOT NULL                    |
| location     | VARCHAR(255) | NOT NULL                    |
| image_url    | VARCHAR(255) | NULL                        |
| user_id      | BIGINT       | Foreign Key, NOT NULL       |
| category_id  | BIGINT       | Foreign Key, NOT NULL       |
| status_id    | BIGINT       | Foreign Key, NOT NULL       |
| priority_id  | BIGINT       | Foreign Key, NOT NULL       |
| created_at   | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   |
| updated_at   | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   |

---

# 7. Foreign Key Relationships

| Child Table | Foreign Key | Parent Table |
| ----------- | ----------- | ------------ |
| complaints  | user_id     | users        |
| complaints  | category_id | categories   |
| complaints  | status_id   | statuses     |
| complaints  | priority_id | priorities   |

---

# 8. Naming Standards

## Tables

- Lowercase
- Plural names

Examples:

- users
- complaints
- categories

---

## Columns

- Lowercase
- snake_case

Examples:

- user_id
- full_name
- created_at

---

## Primary Keys

Format:

table_name_id

Examples:

- user_id
- complaint_id
- category_id

---

## Foreign Keys

Use the primary key name of the referenced table.

Examples:

- user_id
- category_id
- status_id
- priority_id

---

# 9. Summary

The database structure follows relational database best practices with normalized tables, consistent naming conventions, and clearly defined relationships. This schema serves as the foundation for MySQL implementation and Spring Boot JPA entity mapping.

---

**End of Table Structure Design**
