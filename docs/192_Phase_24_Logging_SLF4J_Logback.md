# Phase 24 – Logging (SLF4J & Logback)

## Objective

Implement professional logging throughout the application using SLF4J and Logback.

---

# Why Logging?

Logging helps developers:

- Debug issues
- Track application flow
- Record user activity
- Monitor production systems
- Diagnose errors

---

# Logging Framework

Spring Boot uses:

- SLF4J (Logging API)
- Logback (Logging Implementation)

No additional dependency is required because Spring Boot Starter already includes Logback.

---

# Log Levels

TRACE

Most detailed logs.

Used for deep debugging.

---

DEBUG

Developer debugging information.

---

INFO

Normal application events.

Examples:

- User registered
- Complaint submitted
- Login successful

---

WARN

Unexpected but recoverable situations.

Examples:

- Invalid login attempt
- Missing optional data

---

ERROR

Application failures.

Examples:

- Database failure
- Exception thrown
- Service unavailable

---

# Best Practices

✔ Never use System.out.println()

✔ Never use System.err.println()

✔ Always use Logger

✔ Never log passwords

✔ Log only useful information

✔ Use INFO for business events

✔ Use WARN for recoverable problems

✔ Use ERROR for failures
