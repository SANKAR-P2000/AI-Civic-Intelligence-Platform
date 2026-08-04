# Phase 26.5 – Login User Failure Unit Tests

## Objective

Verify that login fails for invalid credentials.

## Test Scenarios

### Scenario 1

- User not found

### Scenario 2

- Invalid password

## Expected Result

- InvalidCredentialsException is thrown
- JWT is not generated
- Refresh token is not created
