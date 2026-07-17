# AI Civic Intelligence Platform (AICIP)

# Backend Package Structure

---

## Purpose

This document defines the package structure used in the Spring Boot backend.

---

## Package Structure

```
com.sankar.aicip
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
│   └── impl
├── util
└── BackendApplication
```

---

## Package Responsibilities

### config

Application configuration classes.

---

### controller

REST API controllers.

---

### dto

Data Transfer Objects used between client and server.

---

### entity

JPA entity classes mapped to database tables.

---

### exception

Custom exceptions and global exception handling.

---

### repository

Spring Data JPA repositories.

---

### security

Authentication and authorization classes.

---

### service

Business logic interfaces.

---

### service.impl

Business logic implementations.

---

### util

Utility and helper classes.

---

## Benefits

- Clear separation of concerns
- Maintainable architecture
- Easier testing
- Scalable project structure

---

**End of Backend Package Structure**
