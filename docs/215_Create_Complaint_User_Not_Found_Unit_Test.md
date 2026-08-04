# Phase 27.3 – Create Complaint User Not Found Test

## Objective

Verify that ComplaintServiceImpl throws an exception when the authenticated user is not found.

## Test Scenario

- Authenticated email exists in SecurityContext
- UserRepository returns Optional.empty()

## Expected Result

- ResourceNotFoundException is thrown
- Complaint is not saved
- Email is not sent
