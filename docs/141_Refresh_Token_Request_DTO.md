# Refresh Token Request DTO

## Objective

Receive the Refresh Token from the client when requesting a new JWT Access Token.

---

## Class

RefreshTokenRequest

Package:

```
com.sankar.aicip.dto.request
```

---

## Field

| Field        | Type   | Validation |
| ------------ | ------ | ---------- |
| refreshToken | String | @NotBlank  |

---

## Example Request

```json
{
  "refreshToken": "91f74462-a209-4b9a-ac14-65e0abbbd168"
}
```

---

## Validation

If the refresh token is missing or blank:

```json
{
  "refreshToken": "Refresh token is required."
}
```

---

## Benefits

- Simple API request structure
- Automatic validation using Bean Validation
- Prevents null or empty refresh token requests
