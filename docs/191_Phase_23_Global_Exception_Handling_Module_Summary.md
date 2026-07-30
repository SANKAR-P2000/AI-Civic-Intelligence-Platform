# Phase 23 – Global Exception Handling Module Summary

## Module Name

Global Exception Handling

---

# Objective

Implement centralized exception handling for the entire backend application to provide consistent, meaningful, and standardized API error responses.

---

# Features Implemented

## 23.1 Exception Handling Design

Designed the overall exception handling architecture.

Completed:

- Centralized exception handling
- Standard error response format
- Error handling strategy

---

## 23.2 ErrorResponse DTO

Created a reusable ErrorResponse DTO.

Fields:

- timestamp
- status
- error
- message
- path
- errors

Purpose:

Provide a common JSON structure for all API errors.

---

## 23.3 GlobalExceptionHandler

Created:

GlobalExceptionHandler

Annotation:

@RestControllerAdvice

Responsibilities:

- Centralized exception handling
- Standard API responses
- Remove duplicate try-catch blocks

---

## 23.4 ResourceNotFoundException

Implemented:

ResourceNotFoundException

HTTP Status:

404 Not Found

Handled scenarios:

- Complaint not found
- User not found
- Refresh token not found

---

## 23.5 Validation Exception Handling

Implemented handling for:

MethodArgumentNotValidException

HTTP Status:

400 Bad Request

Returns:

- Validation failed message
- Field-level validation errors

---

## 23.6 EmailAlreadyExistsException

Implemented handling for:

EmailAlreadyExistsException

HTTP Status:

409 Conflict

Purpose:

Prevent duplicate user registration.

---

## 23.7 Authentication & Authorization

Implemented:

- InvalidCredentialsException
- AccessDeniedException

HTTP Status:

- 401 Unauthorized
- 403 Forbidden

---

## 23.8 Exception Testing

Verified using Postman.

Completed Tests:

- Validation Error
- Resource Not Found
- Email Already Exists
- Invalid Login
- Unauthorized Request
- Access Denied

All tests passed successfully.

---

# Technologies Used

- Java 25
- Spring Boot
- Spring Security
- JWT
- Bean Validation
- Lombok
- Maven
- Postman

---

# Design Patterns

- DTO Pattern
- Exception Pattern
- REST API Design
- Layered Architecture
- Centralized Error Handling

---

# Learning Outcomes

Successfully learned:

- Custom Exceptions
- Global Exception Handling
- @RestControllerAdvice
- @ExceptionHandler
- Validation Handling
- Authentication Exception Handling
- Authorization Exception Handling
- REST Error Response Design
- Professional API Error Handling

---

# Module Status

Phase 23

COMPLETED

Status:

PASS ✅

Ready for:

Phase 24 – Logging (SLF4J & Logback)
