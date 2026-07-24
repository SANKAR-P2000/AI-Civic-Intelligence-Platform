# Spring Mail Configuration

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

Phase 17 – Notifications & Email Module

---

# Objective

Configure Spring Boot Mail to send emails using Gmail SMTP.

---

# Dependency

spring-boot-starter-mail

---

# SMTP Server

Host

smtp.gmail.com

Port

587

Protocol

SMTP

Encryption

STARTTLS

---

# Configuration

spring.mail.host=smtp.gmail.com

spring.mail.port=587

spring.mail.username=your-email@gmail.com

spring.mail.password=Google App Password

---

# Security

Google App Password is used instead of the normal Gmail password.

---

# Benefits

- Real email delivery
- Secure SMTP authentication
- Production-ready architecture

---

---

# Google App Password

## Important

Google App Passwords are displayed **only once** when they are created.

After closing the page, the password cannot be viewed again.

---

## If You Already Copied It

Paste the App Password into:

```properties
spring.mail.password=YOUR_APP_PASSWORD
```

Do not include spaces.

Example

```
abcd efgh ijkl mnop
```

becomes

```
abcdefghijklmnop
```

---

## If You Forgot the Password

Create a new App Password.

Steps

1. Open

https://myaccount.google.com/apppasswords

2. Sign in to your Google account.

3. Enter an app name.

Example

```
AICIP
```

4. Click

```
Create
```

5. Copy the generated 16-character password immediately.

6. Update

```properties
spring.mail.password=NEW_APP_PASSWORD
```

---

## Security Best Practices

- Never use your normal Gmail password.
- Always use a Google App Password.
- Do not commit App Passwords to GitHub.
- If an App Password is exposed, delete it and generate a new one immediately.

# Status

Phase 17.2

Completed
