# AI Civic Intelligence Platform (AICIP)

# Environment Setup Guide

---

## Purpose

This document explains how to configure the project for local development while keeping sensitive information such as database passwords and JWT secret keys out of the public GitHub repository.

The project follows a professional configuration strategy using:

- Safe `application.properties` (tracked by Git)
- Local `application-local.properties` (ignored by Git)
- Spring Boot Local Profile
- IntelliJ IDEA Run Configuration

---

# Project Structure

```
backend
└── src
    └── main
        └── resources
            ├── application.properties
            └── application-local.properties
```

---

# Why Two Configuration Files?

## application.properties

This file is committed to GitHub.

It contains only placeholder values.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/aicip_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

jwt.secret=your_jwt_secret_key
jwt.expiration=86400000
```

This file is safe to share publicly.

---

## application-local.properties

This file is **never uploaded** to GitHub.

It contains your real credentials.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/aicip_db
spring.datasource.username=root
spring.datasource.password=YOUR_REAL_PASSWORD

jwt.secret=YOUR_REAL_JWT_SECRET
jwt.expiration=86400000
```

---

# Why Keep application-local.properties?

It protects sensitive information such as:

- MySQL password
- JWT Secret Key

Only your local computer should contain this file.

---

# Ignore application-local.properties

Open

```
backend/.gitignore
```

Add:

```gitignore
src/main/resources/application-local.properties
```

This prevents Git from uploading the file.

Verify:

```bash
git status
```

You should NOT see:

```
application-local.properties
```

If the file is not listed, Git is ignoring it correctly.

---

# IntelliJ IDEA Configuration

This project uses IntelliJ IDEA's **Active Profiles** feature.

Do NOT hard-code:

```properties
spring.profiles.active=local
```

inside `application.properties`.

Instead, configure the profile only in IntelliJ.

---

## Step 1

Open IntelliJ IDEA.

---

## Step 2

Click the Run Configuration dropdown.

Example:

```
BackendApplication ▼
```

---

## Step 3

Select:

```
Edit Configurations...
```

---

## Step 4

Select the Spring Boot configuration.

```
BackendApplication
```

---

## Step 5

Locate:

```
Active profiles
```

Enter:

```
local
```

Example:

```
Active profiles

local
```

---

## Step 6

Click

```
Apply
```

↓

```
OK
```

---

# How Spring Boot Loads Configuration

When the application starts:

```
application.properties

↓

application-local.properties

↓

application-local.properties overrides
application.properties
```

Therefore:

GitHub contains:

```
your_mysql_password
```

Your application actually uses:

```
YOUR_REAL_PASSWORD
```

because the local profile overrides the placeholder values.

---

# Verify the Active Profile

Run the application.

The console should display:

```
The following 1 profile is active: "local"
```

This confirms the configuration is correct.

---

# GitHub Workflow

Before pushing to GitHub:

- Ensure `application.properties` contains placeholder values.
- Ensure `application-local.properties` is ignored.

Commit normally:

```bash
git add .
git commit -m "chore: update project configuration"
git push
```

GitHub will contain:

```
application.properties
```

GitHub will NOT contain:

```
application-local.properties
```

---

# Security Notes

Never upload:

- Real MySQL password
- Production JWT secret
- API keys
- Environment secrets

For production deployments, secrets should be stored using:

- Environment Variables
- Secret Managers
- Cloud Configuration Services

---

# Benefits

- Secure public repository
- Professional project structure
- Easy local development
- Protects sensitive credentials
- Follows Spring Boot best practices

---

# Summary

The AI Civic Intelligence Platform separates public configuration from local secrets.

- `application.properties` provides a safe template for GitHub.
- `application-local.properties` stores real local credentials.
- IntelliJ IDEA activates the `local` profile during development.
- Sensitive information never reaches the public repository.

---

**End of Environment Setup Guide**
