# Phase 15 – Public Complaint Tracking & Dashboard

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Implement complaint tracking and dashboard analytics for administrators, government officials, and citizens.

---

# Features Implemented

## Complaint Tracking

- Track complaint by ID
- Secure API using JWT
- Returns complete complaint information

---

## Dashboard Statistics

Provides:

- Total Complaints
- Pending Complaints
- Under Review Complaints
- In Progress Complaints
- Resolved Complaints
- Rejected Complaints

---

## Category Analytics

Returns complaint count grouped by category.

Examples:

- ROAD_DAMAGE
- WATER_SUPPLY
- GARBAGE
- STREET_LIGHT
- DRAINAGE

---

## Status Analytics

Returns complaint count grouped by complaint status.

Statuses:

- PENDING
- UNDER_REVIEW
- IN_PROGRESS
- RESOLVED
- REJECTED

---

# Security

JWT Authentication

Role-Based Authorization

ADMIN

GOVERNMENT

CITIZEN

---

# REST APIs

GET /api/dashboard/stats

GET /api/dashboard/categories

GET /api/dashboard/status

GET /api/complaints/track/{id}

---

# Architecture

Controller

↓

Service

↓

Repository

↓

MySQL

↓

JSON Response

---

# Technologies Used

- Java 25
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Postman

---

# Learning Outcomes

After completing this phase you learned:

- Dashboard aggregation using JPQL
- Constructor Expressions
- Analytics DTOs
- Secure REST APIs
- Complaint tracking
- Role-based dashboard access
- Layered architecture

---

# Result

Phase 15 has been successfully completed.

The project now supports secure complaint tracking and dashboard analytics for authorized users.

---

# Status

Phase 15

Completed
