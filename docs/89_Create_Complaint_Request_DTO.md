# CreateComplaintRequest DTO

## Objective

Create a Data Transfer Object (DTO) for receiving complaint creation requests from citizens.

---

## Fields

- title
- description
- category
- location
- imageUrl

---

## Validation

### title

- Required
- Maximum 150 characters

### description

- Required
- Maximum 1000 characters

### category

- Required

### location

- Required
- Maximum 255 characters

### imageUrl

- Optional

---

## Benefits

- Prevents exposing entity directly.
- Provides request validation.
- Improves API security.
- Keeps API contracts separate from database models.

---

## Status

✅ Completed
