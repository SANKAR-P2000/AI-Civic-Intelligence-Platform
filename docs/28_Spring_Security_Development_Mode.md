# AI Civic Intelligence Platform (AICIP)

# Spring Security (Development Mode)

---

## Purpose

During development, we temporarily allow all HTTP requests so that we can build and test REST APIs without authentication.

---

## Why?

Spring Security protects every endpoint by default.

Without configuration, requests return:

- 401 Unauthorized
- 403 Forbidden

This prevents API testing.

---

## Current Configuration

- CSRF Disabled
- All Requests Permitted
- Authentication Disabled for Development

---

## Important

This configuration is only for development.

In a later phase, it will be replaced with:

- JWT Authentication
- Role-Based Authorization
- Secure Endpoints

---

**End of Spring Security (Development Mode)**
