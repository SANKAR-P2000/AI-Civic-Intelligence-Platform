# AI Civic Intelligence Platform (AICIP)

# Bean Validation

---

## Purpose

Bean Validation ensures that incoming request data meets predefined rules before the business logic is executed.

---

## Validation Annotations Used

### @NotBlank

Ensures that a field is not null, empty, or only whitespace.

---

### @Size

Checks the minimum and maximum length of a string.

---

### @Email

Validates that a string follows a valid email format.

---

### @Pattern

Validates a string using a regular expression.

For the phone number:

- Exactly 10 digits
- Numbers only

---

## Validation Rules

| Field       | Rule                           |
| ----------- | ------------------------------ |
| fullName    | Required, 3–100 characters     |
| email       | Required, valid email          |
| password    | Required, minimum 8 characters |
| phoneNumber | Required, exactly 10 digits    |

---

## Benefits

- Cleaner code
- Automatic validation
- Better error handling
- Consistent API behavior
- Easier maintenance

---

**End of Bean Validation**
