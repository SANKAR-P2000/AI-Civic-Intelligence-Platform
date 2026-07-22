# Enable Method-Level Security

## Objective

Enable Spring Security method-level authorization using annotations.

---

## Annotation

```java
@EnableMethodSecurity
```

---

## File

```
SecurityConfig.java
```

---

## Purpose

Allows the use of authorization annotations such as:

```java
@PreAuthorize
```

```java
@PostAuthorize
```

```java
@Secured
```

---

## Benefits

- Fine-grained access control.
- Secure individual methods.
- Role-based authorization.
- Cleaner security configuration.

---

## Example

```java
@PreAuthorize("hasRole('ADMIN')")
```

Only authenticated users with the ADMIN role can execute the method.

---

## Status

✅ Completed
