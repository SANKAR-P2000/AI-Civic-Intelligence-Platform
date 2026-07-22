# Create Complaint API

## Endpoint

POST /api/complaints

---

## Access

CITIZEN

---

## Authentication

JWT Token Required

Authorization

Bearer <JWT_TOKEN>

---

## Request

```json
{
  "title": "Large pothole on Main Road",
  "description": "A large pothole is causing accidents.",
  "category": "ROAD_DAMAGE",
  "location": "Main Road, Chennai",
  "imageUrl": "https://example.com/pothole.jpg"
}
```

---

## Response

Returns the created complaint with:

- Complaint ID
- Category
- Status
- Citizen Information
- Created Timestamp

---

## Database Verification

```sql
SELECT * FROM complaints;
```

Verify:

- Complaint saved
- Status = PENDING
- citizen_id populated

---

## Status

✅ Completed
