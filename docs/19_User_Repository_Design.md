# AI Civic Intelligence Platform (AICIP)

# User Repository Design

---

## Purpose

The UserRepository provides database operations for the User entity.

It acts as the data access layer between the service layer and the MySQL database.

---

## Interface

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
```

---

## Managed Entity

- User

---

## Primary Key Type

- Long

---

## Built-in CRUD Methods

- save()
- findById()
- findAll()
- deleteById()
- existsById()
- count()

---

## Future Custom Methods

```java
Optional<User> findByEmail(String email);

boolean existsByEmail(String email);
```

---

## Benefits

- No SQL required for CRUD operations.
- Automatic implementation by Spring Data JPA.
- Cleaner and more maintainable code.
- Supports custom query generation from method names.

---

**End of User Repository Design**
