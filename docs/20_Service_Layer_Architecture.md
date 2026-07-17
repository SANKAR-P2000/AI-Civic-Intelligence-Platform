# AI Civic Intelligence Platform (AICIP)

# Service Layer Architecture

---

## Purpose

The Service Layer contains the application's business logic.

It acts as a bridge between:

Controller
↓

Repository

---

## Responsibilities

- Business Rules
- Validation
- Data Processing
- Calling Repository Methods
- Returning DTOs

---

## Why Use a Service Layer?

Without Service Layer:

Controller → Repository

With Service Layer:

Controller
↓

Service
↓

Repository

This keeps controllers lightweight and business logic reusable.

---

## Planned Services

- UserService
- ComplaintService
- CategoryService
- NotificationService
- DashboardService

---

**End of Service Layer Architecture**
