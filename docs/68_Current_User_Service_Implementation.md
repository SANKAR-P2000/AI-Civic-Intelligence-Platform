# Current User Service Implementation

## Objective

Implement the business logic for retrieving the currently authenticated user's profile.

---

## Method

```java
CurrentUserResponse getCurrentUser(String email)
```

---

## Process

1. Receive the authenticated user's email.
2. Search the database using the email.
3. Throw an exception if the user is not found.
4. Create a CurrentUserResponse DTO.
5. Copy user information into the DTO.
6. Return the DTO to the Controller.

---

## Returned Fields

- id
- fullName
- email
- phoneNumber
- role
- createdAt

---

## Benefits

- Keeps database logic inside the Service layer.
- Prevents exposing the User entity directly.
- Returns only the required information.
- Maintains a clean layered architecture.

---

**Status**

✅ Completed
