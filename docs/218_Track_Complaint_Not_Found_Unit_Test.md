# Phase 27.6 – Track Complaint Not Found Unit Test

## Objective

Verify that trackComplaint() throws ResourceNotFoundException when the complaint does not exist.

## Test Scenario

- Complaint ID is invalid
- Repository returns Optional.empty()

## Expected Result

- ResourceNotFoundException is thrown
- Repository interaction verified
