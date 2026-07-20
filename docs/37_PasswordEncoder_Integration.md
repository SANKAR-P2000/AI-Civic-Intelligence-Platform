# AI Civic Intelligence Platform (AICIP)

# PasswordEncoder Integration

---

## Purpose

The PasswordEncoder is injected into the service layer to hash passwords before storing them in the database.

---

## Dependency Injection

```java
private final PasswordEncoder passwordEncoder;
```

Spring automatically injects the PasswordEncoder bean created in PasswordConfig.

---

## Password Encoding

Instead of:

```java
user.setPassword(request.getPassword());
```

We use:

```java
user.setPassword(passwordEncoder.encode(request.getPassword()));
```

---

## Benefits

- Plain passwords are never stored.
- Passwords are hashed using BCrypt.
- Improved application security.
- Follows Spring Security best practices.

---

## Flow

Client Password

↓

PasswordEncoder

↓

BCrypt Hash

↓

Database

---

**End of PasswordEncoder Integration**
