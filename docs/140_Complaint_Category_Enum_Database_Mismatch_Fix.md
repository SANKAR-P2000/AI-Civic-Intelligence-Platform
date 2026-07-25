# Complaint Category Enum Database Mismatch Fix

## Overview

During the implementation of the Complaint Module, a database error occurred after adding a new complaint category (`ANIMAL_CONTROL`) to the application.

This document explains the problem, root cause, debugging process, and final solution.

---

# Issue

While submitting a complaint using the following request:

```json
{
  "title": "Stray Dogs Creating Safety Concern",
  "description": "A group of stray dogs has been chasing pedestrians.",
  "category": "ANIMAL_CONTROL",
  "location": "Chromepet, Chennai",
  "imageUrl": "stray-dogs.jpg"
}
```

Spring Boot returned an exception.

Application Log:

```
Data truncated for column 'category' at row 1
```

Complaint creation failed.

---

# Root Cause

Initially, the Java Enum was updated.

ComplaintCategory.java

```java
public enum ComplaintCategory {

    GARBAGE,
    DRAINAGE,
    ROAD_DAMAGE,
    STREET_LIGHT,
    TRAFFIC,
    WATER_SUPPLY,
    PUBLIC_TRANSPORT,
    ENVIRONMENT,
    ANIMAL_CONTROL,
    OTHER

}
```

However, the MySQL table still contained the old ENUM values.

Database:

```
ENUM(
'GARBAGE',
'DRAINAGE',
'ROAD_DAMAGE',
'STREET_LIGHT',
'TRAFFIC',
'WATER_SUPPLY',
'PUBLIC_TRANSPORT',
'ENVIRONMENT',
'OTHER'
)
```

Notice that:

```
ANIMAL_CONTROL
```

was missing.

Hibernate attempted to insert

```
ANIMAL_CONTROL
```

MySQL rejected it because the value did not exist in the table definition.

---

# Debugging Steps

## Step 1

Checked Spring Boot logs.

Observed:

```
Data truncated for column 'category'
```

---

## Step 2

Verified JWT Authentication.

Result:

✅ Working

---

## Step 3

Verified Controller.

Result:

✅ Working

---

## Step 4

Verified Repository.

Result:

✅ Working

---

## Step 5

Executed:

```sql
SHOW COLUMNS FROM complaints;
```

Observed that the database ENUM did not contain:

```
ANIMAL_CONTROL
```

Root cause identified.

---

# Solution

Updated MySQL ENUM.

```sql
ALTER TABLE complaints
MODIFY COLUMN category ENUM(
'GARBAGE',
'DRAINAGE',
'ROAD_DAMAGE',
'STREET_LIGHT',
'TRAFFIC',
'WATER_SUPPLY',
'PUBLIC_TRANSPORT',
'ENVIRONMENT',
'ANIMAL_CONTROL',
'OTHER'
);
```

---

# Verification

Executed

```sql
SHOW COLUMNS FROM complaints;
```

Result:

```
ENUM(
'GARBAGE',
'DRAINAGE',
'ROAD_DAMAGE',
'STREET_LIGHT',
'TRAFFIC',
'WATER_SUPPLY',
'PUBLIC_TRANSPORT',
'ENVIRONMENT',
'ANIMAL_CONTROL',
'OTHER'
)
```

Database successfully updated.

---

# Testing

Submitted Complaint:

```json
{
  "title": "Stray Dogs Creating Safety Concern",
  "description": "A group of stray dogs has been chasing pedestrians.",
  "category": "ANIMAL_CONTROL",
  "location": "Chromepet, Chennai",
  "imageUrl": "stray-dogs.jpg"
}
```

Result:

```
HTTP 200 OK
```

Complaint saved successfully.

---

# Email Verification

After successful complaint submission:

✔ Complaint stored in MySQL

↓

✔ Complaint ID generated

↓

✔ HTML Email sent successfully

↓

✔ Citizen received complaint confirmation email

---

# Lessons Learned

Whenever a Java Enum is modified:

1. Update the Java Enum.
2. Update the MySQL ENUM column.
3. Restart Spring Boot.
4. Verify using:

```sql
SHOW COLUMNS FROM complaints;
```

Never update only one side.

The Java Enum and MySQL ENUM must always remain synchronized.

---

# Final Status

| Component              | Status     |
| ---------------------- | ---------- |
| ComplaintCategory Enum | ✅ Fixed   |
| MySQL ENUM             | ✅ Updated |
| Complaint Submission   | ✅ Working |
| Database Storage       | ✅ Working |
| Email Notification     | ✅ Working |
| HTML Email Template    | ✅ Working |

---

## Conclusion

The issue was caused by a mismatch between the Java Enum and the MySQL ENUM definition.

After synchronizing both, complaint submission, database persistence, and HTML email notifications worked successfully.
