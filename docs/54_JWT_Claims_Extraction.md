# JWT Claims Extraction

## Purpose

JWT stores information called **Claims**.

The JwtService provides reusable methods to extract information from a signed JWT.

---

## Implemented Methods

### extractUsername()

Returns the Subject stored inside the JWT.

For this project:

```
Subject = User Email
```

---

### extractClaim()

Generic method used to extract any claim.

Examples:

- Subject
- Expiration
- Issued At

---

### extractAllClaims()

Parses the JWT.

Verifies:

- Signature
- Token Integrity

Returns all claims contained in the token.

---

## Flow

```
JWT
 │
 ▼
extractAllClaims()
 │
 ▼
Claims
 │
 ▼
extractUsername()
```

---

## Advantages

- Reusable
- Centralized
- Easy to extend
- Supports future custom claims

---

**End of JWT Claims Extraction**
