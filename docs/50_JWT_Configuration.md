# AI Civic Intelligence Platform (AICIP)

# JWT Configuration

---

## Purpose

JWT requires a secret key to digitally sign tokens and an expiration time to control how long a token remains valid.

These settings are stored in the Spring Boot configuration file.

---

## Configuration

```properties
jwt.secret=JWT_SECRET_KEY_FOR_AICIP_PROJECT_2026_SUPER_SECURE_BACKEND_SECRET_KEY_123456789
jwt.expiration=86400000
```

---

## jwt.secret

The secret key is used to:

- Sign JWT tokens
- Verify JWT signatures
- Prevent token tampering

Only the backend application should know this key.

---

## jwt.expiration

```
86400000 milliseconds
```

Equals:

```
24 Hours
```

After this period, the JWT becomes invalid and the user must log in again.

---

## Security Notes

For production environments:

- Do not hard-code secrets.
- Store secrets in environment variables or a secure vault.
- Rotate secrets periodically.

---

## Summary

The JWT configuration defines the secret key used for signing tokens and the expiration time used to limit token validity.

---

**End of JWT Configuration**
