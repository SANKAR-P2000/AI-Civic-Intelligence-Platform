# ComplaintStatus Enum

## Project

AI Civic Intelligence Platform (AICIP)

---

# Objective

Create an enumeration representing the lifecycle of a complaint.

Using an enum ensures that only predefined complaint statuses can be stored within the application and database.

---

# ComplaintStatus Enum

```java
public enum ComplaintStatus {

    PENDING,

    UNDER_REVIEW,

    IN_PROGRESS,

    RESOLVED,

    REJECTED
}
```

---

# Status Descriptions

## PENDING

The complaint has been submitted successfully and is waiting for review.

---

## UNDER_REVIEW

The complaint is currently being reviewed by a Government Officer or Administrator.

---

## IN_PROGRESS

The complaint has been accepted and the resolution process has started.

---

## RESOLVED

The complaint has been successfully resolved.

---

## REJECTED

The complaint has been rejected because it is invalid, duplicated, incomplete, or outside the organization's scope.

---

# Complaint Status Flow

```
Citizen
     │
     ▼
PENDING
     │
     ▼
UNDER_REVIEW
     │
     ▼
IN_PROGRESS
     │
     ▼
RESOLVED
```

Alternative Flow

```
PENDING
     │
     ▼
REJECTED
```

---

# Why Use Enum?

Without an enum

```
Pending
pending
PENDING
Pendng
```

These inconsistent values create validation and reporting issues.

With an enum

```
ComplaintStatus.PENDING

ComplaintStatus.RESOLVED

ComplaintStatus.REJECTED
```

Only valid predefined values are allowed.

---

# Benefits

- Type Safety
- Better Readability
- Consistent Database Values
- Easier Validation
- Cleaner Business Logic
- Simplified Reporting

---

# Future Usage

ComplaintStatus will be used in:

- Complaint Entity
- Complaint Service
- Complaint Controller
- Admin Dashboard
- Government Dashboard
- Complaint Analytics
- Power BI Reports

---

# Learning Outcomes

After completing this phase, I learned:

- How Java Enums improve application design.
- Why enums are preferred over String constants.
- How enums improve maintainability and validation.
- How complaint lifecycle is represented using enums.

---

# Status

```
Phase 14.2

ComplaintStatus Enum

STATUS

COMPLETED
```
