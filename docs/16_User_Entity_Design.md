# AI Civic Intelligence Platform (AICIP)

# User Entity Design

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Document | User Entity Design                     |
| Version  | 1.0                                    |
| Status   | Completed                              |

---

# 1. Purpose

The **User** entity represents every registered user in the system.

A user can be either:

- Citizen
- Administrator

The User entity is responsible for storing authentication and basic profile information.

---

# 2. Entity Information

| Entity Name | Database Table |
| ----------- | -------------- |
| User        | users          |

---

# 3. Java Class

```java
@Entity
@Table(name = "users")
public class User {
}
```

---

# 4. Fields

| Field       | Data Type     | Constraints                 | Description                                  |
| ----------- | ------------- | --------------------------- | -------------------------------------------- |
| id          | Long          | Primary Key, Auto Increment | Unique identifier                            |
| fullName    | String        | Not Null                    | User's full name                             |
| email       | String        | Not Null, Unique            | Login email                                  |
| password    | String        | Not Null                    | User password (encrypted later using BCrypt) |
| phoneNumber | String        | Not Null                    | Contact number                               |
| role        | UserRole      | Not Null                    | User role (CITIZEN or ADMIN)                 |
| createdAt   | LocalDateTime | Not Null                    | Account creation timestamp                   |

---

# 5. JPA Annotations Used

## @Entity

Marks the Java class as a JPA Entity.

---

## @Table(name = "users")

Maps the entity to the **users** table.

---

## @Id

Marks the primary key.

---

## @GeneratedValue(strategy = GenerationType.IDENTITY)

Automatically generates the primary key value.

---

## @Column(nullable = false)

Ensures the database column cannot contain NULL values.

---

## @Column(unique = true)

Ensures duplicate email addresses cannot be stored.

---

## @Enumerated(EnumType.STRING)

Stores enum values as readable text in the database.

Example:

```
CITIZEN
ADMIN
```

instead of

```
0
1
```

---

# 6. Database Structure

The generated table will contain approximately:

| Column       |
| ------------ |
| id           |
| full_name    |
| email        |
| password     |
| phone_number |
| role         |
| created_at   |

---

# 7. Why Email is Unique

Every user must log in using a unique email address.

The database prevents duplicate registrations by enforcing a UNIQUE constraint.

---

# 8. Why UserRole Enum is Used

Instead of allowing any text value, the application restricts roles to predefined values.

Allowed values:

- CITIZEN
- ADMIN

Benefits:

- Type Safety
- Cleaner Code
- Prevents Invalid Values
- Easier Maintenance

---

# 9. Why createdAt is Stored

The creation timestamp helps:

- Audit user registrations
- Display registration date
- Generate reports
- Track account history

The value is automatically populated using the JPA `@PrePersist` lifecycle callback.

---

# 10. Entity Relationships

Current Version:

```
User
```

Future Versions:

```
User
   │
   ├── Complaint (One-to-Many)
   ├── Notification
   └── Activity Log
```

---

# 11. Benefits of This Design

- Clean and maintainable
- Supports authentication
- Supports role-based authorization
- Easy to extend
- Compatible with Spring Security

---

# 12. Next Step

Create the **UserRepository** interface to perform database operations without writing SQL.

---

**End of User Entity Design**
