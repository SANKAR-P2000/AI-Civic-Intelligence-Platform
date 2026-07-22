# Current User Service Interface

## Objective

Define the service contract for retrieving the currently authenticated user's information.

---

## Method

```java
CurrentUserResponse getCurrentUser(String email);
```

---

## Parameter

| Name  | Type   | Description                                      |
| ----- | ------ | ------------------------------------------------ |
| email | String | Email extracted from the authenticated JWT token |

---

## Return Type

```
CurrentUserResponse
```

---

## Purpose

The method retrieves the authenticated user's information from the database and returns it as a DTO.

---

## Benefits

- Keeps business logic inside the Service layer.
- Maintains Controller-Service separation.
- Follows layered architecture.

---

**Status**

✅ Completed
