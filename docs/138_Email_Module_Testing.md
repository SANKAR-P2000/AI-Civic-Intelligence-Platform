# Email Module Testing

## Project

AI Civic Intelligence Platform (AICIP)

---

# Phase

17.9 – Email Module Testing

---

# Objective

Verify all email notification workflows and exception handling.

---

# Test Cases

## Test Case 1

### Scenario

Citizen creates a complaint using a valid Gmail address.

### Expected Result

- Complaint saved successfully.
- Complaint Submission HTML Email received.
- API returns HTTP 200.

### Status

PASS

---

## Test Case 2

### Scenario

Admin updates complaint status.

### Expected Result

- Complaint status updated.
- Complaint Status HTML Email received.
- API returns HTTP 200.

### Status

PASS

---

## Test Case 3

### Scenario

Recipient email does not exist.

### Expected Result

- Complaint saved successfully.
- Email sending fails.
- Error logged.
- API still returns HTTP 200.

### Status

PASS

---

## Test Case 4

### Scenario

SMTP configuration is incorrect.

### Expected Result

- Complaint saved successfully.
- Email sending fails.
- Error logged.
- Application continues running.

### Status

PASS

---

## Test Case 5

### Scenario

HTML Email Rendering

### Expected Result

- Email displays correctly.
- Header visible.
- Complaint details visible.
- Status badge visible.
- Footer visible.

### Status

PASS

---

# Summary

| Feature                    | Result |
| -------------------------- | ------ |
| Complaint Submission Email | PASS   |
| Complaint Status Email     | PASS   |
| HTML Rendering             | PASS   |
| Gmail SMTP                 | PASS   |
| Exception Handling         | PASS   |
| Reusable Template          | PASS   |

---

# Overall Result

Email Notification Module Tested Successfully.

---

# Status

Completed

# Test Case 1

## Scenario

A citizen logs into the AI Civic Intelligence Platform and submits a new complaint using a valid Gmail email address.

---

## Preconditions

- Spring Boot application is running.
- MySQL database is connected.
- Gmail SMTP is configured correctly.
- Citizen account exists.
- Citizen email is a valid Gmail address.
- Citizen is logged in and has a valid JWT token.

Example Citizen

Name: VijayCM

Email: esportspubgnewstate@gmail.com

Role: CITIZEN

---

## API

POST /api/complaints

---

## Authorization

Bearer <Citizen JWT Token>

---

## Request Body

```json
{
  "title": "Garbage Overflow",
  "description": "Garbage has not been collected for three days.",
  "category": "GARBAGE",
  "location": "Anna Nagar, Chennai",
  "imageUrl": "a6a8e82b-44d3-4e4d-b35c-2c66f1f4d8c9.png"
}
```

---

## Expected API Response

HTTP Status

200 OK

Example Response

```json
{
  "id": 7,
  "title": "Garbage Overflow",
  "description": "Garbage has not been collected for three days.",
  "category": "GARBAGE",
  "status": "PENDING",
  "location": "Anna Nagar, Chennai",
  "imageUrl": "a6a8e82b-44d3-4e4d-b35c-2c66f1f4d8c9.png",
  "citizenName": "VijayCM"
}
```

---

## Database Verification

Table

users

Citizen Email

esportspubgnewstate@gmail.com

Complaint Table

A new complaint record should be inserted with:

- Complaint ID generated automatically
- Status = PENDING
- Citizen ID mapped correctly
- Image URL stored correctly

---

## Email Verification

Recipient

esportspubgnewstate@gmail.com

Subject

Complaint Submitted Successfully

Expected Email Content

- AI Civic Intelligence Platform title
- Greeting with citizen name
- Complaint ID
- Complaint Category
- Complaint Status
- Thank you message
- Footer

---

## Expected Result

✓ Complaint saved successfully.

✓ HTTP 200 returned.

✓ HTML email received.

✓ Complaint visible in MySQL database.

✓ Email content rendered correctly.

---

## Actual Result

PASS

---

## Remarks

The email was successfully delivered using Gmail SMTP.

In the development environment, Gmail may initially place the email in the Spam folder. Marking it as "Not Spam" helps future emails appear in the Inbox.
