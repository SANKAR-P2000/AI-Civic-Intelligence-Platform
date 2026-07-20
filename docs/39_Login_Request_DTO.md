# AI Civic Intelligence Platform (AICIP)

# Login Request DTO

---

## Purpose

The `LoginRequest` DTO is used to receive login credentials from the client.

It transfers the user's email and password from the HTTP request body to the backend application.

The DTO does not contain any business logic or database operations.

---

## Why Do We Need a DTO?

Instead of exposing the database entity directly, the application uses a Data Transfer Object (DTO) to:

- Receive only the required fields.
- Validate user input.
- Improve security.
- Separate API models from database entities.

---

## Fields

| Field    | Type   | Description                     |
| -------- | ------ | ------------------------------- |
| email    | String | User's registered email address |
| password | String | User's login password           |

---

## Validation

The LoginRequest DTO uses Bean Validation annotations.

### Email

```java
@NotBlank(message = "Email is required.")
@Email(message = "Invalid email format.")
```

Ensures:

- Email cannot be empty.
- Email must be in a valid format.

---

### Password

```java
@NotBlank(message = "Password is required.")
```

Ensures:

- Password cannot be empty.

---

## Example Request

```json
{
  "email": "anand@gmail.com",
  "password": "password123"
}
```

---

## Request Flow

Client

↓

POST /api/users/login

↓

LoginRequest DTO

↓

UserController

↓

UserService

↓

Authentication Logic

---

## Benefits

- Clean API design
- Input validation
- Better security
- Easy maintenance
- Separation of concerns

---

## Next Step

The LoginRequest DTO is used by the Login API to authenticate users using their email and password.

---

**End of Login Request DTO**
