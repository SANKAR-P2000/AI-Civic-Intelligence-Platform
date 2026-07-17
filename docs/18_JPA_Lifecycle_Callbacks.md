# AI Civic Intelligence Platform (AICIP)

# JPA Lifecycle Callbacks

---

## Purpose

JPA lifecycle callbacks allow methods to execute automatically during different stages of an entity's lifecycle.

---

## @PrePersist

Executes before an entity is inserted into the database.

Example:

```java
@PrePersist
public void prePersist() {
    this.createdAt = LocalDateTime.now();
}
```

---

## Benefits

- Automatically initializes values.
- Reduces duplicate code.
- Ensures consistency.
- Commonly used for timestamps.

---

## Future Lifecycle Callbacks

- @PostPersist
- @PreUpdate
- @PostUpdate
- @PreRemove
- @PostRemove
- @PostLoad

These will be introduced when required.

---

**End of JPA Lifecycle Callbacks**
