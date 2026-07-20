# AI Civic Intelligence Platform (AICIP)

# Duplicate Email Check

---

## Purpose

Before creating a new user, the application checks whether the email address already exists.

This prevents duplicate accounts.

---

## Repository Method

```java
Optional<User> findByEmail(String email);
```

Spring Data JPA automatically creates the SQL query based on the method name.

Equivalent SQL:

```sql
SELECT * FROM users WHERE email = ?;
```

---

## Why Optional?

`Optional<User>` clearly represents two possible outcomes:

- User exists.
- User does not exist.

It helps avoid null checks and makes the code easier to read.

---

## Benefits

- Prevents duplicate registrations.
- Improves user experience.
- Reduces unnecessary database exceptions.
- Keeps business logic clear.

---

**End of Duplicate Email Check**
