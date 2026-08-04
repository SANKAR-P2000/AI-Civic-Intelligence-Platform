# Phase 27.9 – Update Complaint Status Not Found Unit Test

## Objective

Verify that updateComplaintStatus() throws ResourceNotFoundException when the complaint does not exist.

## Test Scenario

- Invalid complaint ID
- Repository returns Optional.empty()

## Expected Result

- ResourceNotFoundException is thrown
- Complaint is not saved
- Email notification is not sent
