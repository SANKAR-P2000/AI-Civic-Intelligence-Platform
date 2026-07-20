# AI Civic Intelligence Platform (AICIP)

# JWT Dependencies

---

## Purpose

The project uses the JJWT library to generate, sign, parse, and validate JSON Web Tokens (JWT).

---

## Dependencies

### jjwt-api

Provides the public API for creating and parsing JWTs.

### jjwt-impl

Contains the runtime implementation used by the JJWT library.

### jjwt-jackson

Uses the Jackson library to serialize and deserialize JWT payloads.

---

## Maven Configuration

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.7</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.7</version>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.7</version>
    <scope>runtime</scope>
</dependency>
```

---

## Benefits

- Secure JWT generation
- JWT signature verification
- JSON payload support
- Compatible with Spring Boot

---

## Summary

The JJWT library provides all the functionality required to implement secure, token-based authentication in the AI Civic Intelligence Platform.

---

**End of JWT Dependencies**
