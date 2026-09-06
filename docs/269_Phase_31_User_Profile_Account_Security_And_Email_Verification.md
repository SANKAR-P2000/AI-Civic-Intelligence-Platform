# Phase 31 — User Profile & Account Security + Email Verification

## 1. Objective
The objective of Phase 31 is to build a complete, resilient user account management foundation on top of the existing Phase 30 authentication system in the AI Civic Intelligence Platform (AICIP). It provides user profile views, profile editing, profile picture management, email verification via OTP (with cooldown and attempt limits), change password functionality, and a structured Account Settings page supporting daylight/night modes across all responsive viewports.

## 2. Existing Architecture
- **Authentication**: JWT stateless authentication with Refresh Tokens.
- **Frontend**: React + Vite + React Router.
- **Backend**: Spring Boot 3.x + Spring Security + Spring Data JPA.
- **Database**: MySQL database storing `users` and `otp_verifications`.
- **OTP System**: SHA-256 OTP hashing with 10-minute expiry and 60-second resend cooldown (`EmailOtpServiceImpl`).
- **File Upload**: Local disk storage using `FileStorageService` saving to `uploads/`.

## 3. Sub-Phase Breakdown
- **31.1 Existing Architecture Audit**: Inspected existing backend entity, repositories, controllers, security configuration, and frontend state.
- **31.2 User Profile Backend API**: Enhanced profile endpoints (`GET /api/users/profile`, `PATCH /api/users/profile`).
- **31.3 User Profile Frontend**: Created `Profile.jsx` and `Profile.css` using the Glassmorphism design system.
- **31.4 Edit Profile**: Implemented profile field updates (`fullName`, `phoneNumber`).
- **31.5 Profile Picture Foundation**: Added avatar preview and image upload endpoint (`POST /api/users/profile/picture`).
- **31.6 Email Verification Architecture**: Reused existing `OtpVerification` entity and OTP infrastructure for email verification.
- **31.7 Email Verification OTP**: Implemented `POST /api/auth/verify-email`.
- **31.8 Resend Verification OTP**: Implemented `POST /api/auth/resend-email-otp` and `POST /api/auth/send-verification-otp`.
- **31.9 Email Verification Status**: Integrated status badges and verify flow into Profile & Settings.
- **31.10 Change Password**: Implemented authenticated `PATCH /api/users/change-password`.
- **31.11 Account Settings Foundation**: Created `Settings.jsx` and `Settings.css` with ACCOUNT, SECURITY, APPEARANCE, and NOTIFICATIONS sections.
- **31.12 Responsive & Theme Verification**: Verified layout responsiveness across 320px–1440px viewports in Daylight and Night modes.
- **31.13 Full Phase 31 Integration Testing**: Verified API endpoints, OTP flows, password updates, and existing Phase 30 functionality.
- **31.14 Documentation & Git Completion**: Created documentation, verified clean builds, and committed changes.

## 4. Backend Changes
- **`User.java`**: Added `emailVerified` (boolean, default `false`), `phoneVerified` (boolean, default `false`), and `profilePictureUrl` (String).
- **`UserProfileResponse.java`**: Created new response DTO for safe profile representation.
- **`UpdateProfileRequest.java`**: Created request DTO for profile updates.
- **`ChangePasswordRequest.java`**: Created request DTO for authenticated password change.
- **`VerifyEmailRequest.java` & `ResendEmailOtpRequest.java`**: Created request DTOs for email OTP verification and resend requests.
- **`CurrentUserResponse.java` & `LoginResponse.java`**: Extended DTOs to include verification status and avatar URL.
- **`UserService.java` & `UserServiceImpl.java`**: Implemented profile retrieval, profile updates, password changes, avatar upload, and email OTP verification.
- **`UserController.java`**: Exposed `/api/users/profile`, `/api/users/profile/picture`, and `/api/users/change-password`.
- **`AuthController.java`**: Exposed `/api/auth/send-verification-otp`, `/api/auth/verify-email`, and `/api/auth/resend-email-otp`.

## 5. Frontend Changes
- **`auth.js`**: Added API service calls (`getProfile`, `updateProfile`, `changePassword`, `uploadProfilePicture`, `sendVerificationOtp`, `verifyEmail`, `resendEmailOtp`).
- **`AuthContext.jsx`**: Exposed profile and verification helper functions in `useAuth`.
- **`Profile.jsx` & `Profile.css`**: Created user profile page displaying avatar, account details, edit modal/form, and email verification OTP modal.
- **`Settings.jsx` & `Settings.css`**: Created Account Settings page with sectioned controls for Account, Security, Appearance, and Notifications.
- **`Navbar.jsx`**: Added `Profile` and `Settings` links for authenticated users.
- **`AppRoutes.jsx`**: Registered `/profile` and `/settings` as protected routes.

## 6. Database Changes
- Updated `users` table schema automatically via JPA hibernate DDL / column mappings:
  - `email_verified` TINYINT(1) DEFAULT 0
  - `phone_verified` TINYINT(1) DEFAULT 0
  - `profile_picture_url` VARCHAR(255) NULL

## 7. API Endpoints
- `GET /api/users/profile` — Get authenticated user profile.
- `PATCH /api/users/profile` — Update user profile full name and phone number.
- `POST /api/users/profile/picture` — Upload avatar image.
- `PATCH /api/users/change-password` — Change authenticated user's password.
- `POST /api/auth/send-verification-otp` — Request email verification OTP.
- `POST /api/auth/verify-email` — Verify email OTP and mark account as verified.
- `POST /api/auth/resend-email-otp` — Resend verification OTP with 60-second cooldown protection.

## 8. Authentication Flow
```
User Requests Protected Profile Endpoint (/api/users/profile)
  ↓
JWT Authentication Filter Intercepts Request
  ↓
Validate Token Signature & Expiry
  ↓
Extract User Principal Email
  ↓
Fetch Profile from Database
  ↓
Return UserProfileResponse
```

## 9. Email Verification Flow
```
Click "Verify Email" on Profile or Settings
  ↓
POST /api/auth/send-verification-otp
  ↓
Generate 6-digit OTP & Hash with SHA-256
  ↓
Console/Mail Delivery & Display for Localhost
  ↓
User Inputs 6-digit OTP
  ↓
POST /api/auth/verify-email
  ↓
Validate Expiry & Attempt Thresholds
  ↓
Mark emailVerified = true in Database
```

## 10. OTP Flow
- **Generation**: SecureRandom 6-digit numeric code.
- **Hashing**: SHA-256 hex string stored in `otp_verifications`.
- **Expiry**: 10 minutes.
- **Attempts**: Maximum 5 failed attempts allowed before invalidation.
- **Cooldown**: 60-second delay enforced between resend requests.

## 11. Change Password Flow
```
Submit Change Password Form (Current, New, Confirm)
  ↓
PATCH /api/users/change-password
  ↓
Verify Current Password via BCrypt Matches
  ↓
Verify New Password Matches Confirmation
  ↓
Encode New Password via BCrypt
  ↓
Update User Entity in MySQL
```

## 12. Profile Picture Architecture
- Uses `FileStorageService` to store uploaded avatar images in local directory `uploads/`.
- `StaticResourceConfiguration` serves files statically at `http://localhost:8080/uploads/...`.
- Fallback avatar initial placeholder rendered when no custom profile picture is uploaded.

## 13. Account Settings
- Sectioned layout:
  - **ACCOUNT**: Quick overview of profile info and email/phone verification status.
  - **SECURITY**: Change password form.
  - **APPEARANCE**: Integrated `ThemeToggle` component.
  - **NOTIFICATIONS**: Placeholder for future notification preferences.

## 14. Responsive Design
Verified on screen widths:
- `320px` (Mobile Small)
- `375px` (Mobile Medium)
- `425px` (Mobile Large)
- `768px` (Tablet)
- `1024px` (Desktop Small)
- `1280px` (Desktop Medium)
- `1440px` (Desktop Large)

## 15. Day/Night Mode
All new UI components use CSS design variables (`var(--bg-glass)`, `var(--text-primary)`, `var(--text-muted)`), ensuring seamless transitions between Daylight Mode and Night Mode.

## 16. Testing
- **Profile API**: Verified GET and PATCH `/api/users/profile`.
- **Email Verification**: Verified OTP generation, failed attempts rejection, expired OTP handling, and successful verification updating `emailVerified = true`.
- **Change Password**: Verified wrong current password rejection, password mismatch rejection, and successful update allowing login with new password.
- **Phase 30 Regression**: Confirmed Login, Registration, Forgot Password, Reset Password, and Protected Routes function normally.

## 17. Problems Encountered
- None. Build succeeded cleanly across backend and frontend.

## 18. Solutions
- Reused existing `FileStorageService` and `EmailOtpServiceImpl` to prevent code duplication and architectural bloat.

## 19. Files Created
1. `backend/src/main/java/com/sankar/aicip/dto/response/UserProfileResponse.java`
2. `backend/src/main/java/com/sankar/aicip/dto/request/UpdateProfileRequest.java`
3. `backend/src/main/java/com/sankar/aicip/dto/request/ChangePasswordRequest.java`
4. `backend/src/main/java/com/sankar/aicip/dto/request/VerifyEmailRequest.java`
5. `backend/src/main/java/com/sankar/aicip/dto/request/ResendEmailOtpRequest.java`
6. `frontend/src/pages/Profile.jsx`
7. `frontend/src/pages/Profile.css`
8. `frontend/src/pages/Settings.jsx`
9. `frontend/src/pages/Settings.css`
10. `docs/269_Phase_31_User_Profile_Account_Security_And_Email_Verification.md`

## 20. Files Modified
1. `backend/src/main/java/com/sankar/aicip/entity/User.java`
2. `backend/src/main/java/com/sankar/aicip/dto/response/CurrentUserResponse.java`
3. `backend/src/main/java/com/sankar/aicip/service/UserService.java`
4. `backend/src/main/java/com/sankar/aicip/service/impl/UserServiceImpl.java`
5. `backend/src/main/java/com/sankar/aicip/controller/UserController.java`
6. `backend/src/main/java/com/sankar/aicip/controller/AuthController.java`
7. `frontend/src/services/auth.js`
8. `frontend/src/context/AuthContext.jsx`
9. `frontend/src/routes/AppRoutes.jsx`
10. `frontend/src/components/navigation/Navbar.jsx`

## 21. Commands Executed
```powershell
.\mvnw.cmd test-compile
Purpose: Compiles backend Java code and verifies zero syntax/type errors.

npm run build
Purpose: Compiles the Vite React application and verifies zero bundle errors.

.\mvnw.cmd clean test
Purpose: Executes the backend unit test suite.
```

## 22. Purpose of Each Important Command
- `.\mvnw.cmd test-compile`: Ensures Spring Boot application classes and DTOs build cleanly.
- `npm run build`: Validates JSX components, CSS imports, and route configurations for frontend deployment.
- `.\mvnw.cmd clean test`: Ensures no regression errors exist in existing test cases.

## 23. Build Verification
- **Frontend Build**: PASS (Vite build completed successfully).
- **Backend Tests**: PASS.

## 24. Git Verification
- Clean status verified prior to commit.

## 25. Completion Checklist
- [x] Profile GET & PATCH API implemented
- [x] Profile UI with avatar preview created
- [x] Email verification OTP flow implemented
- [x] Resend OTP with 60s cooldown implemented
- [x] Verification status badges implemented
- [x] Change password API & UI implemented
- [x] Account Settings page implemented
- [x] Responsive layout & Theme support verified
- [x] Frontend build & Backend test suite PASSED

## 26. Next Phase Recommendation
Proceed to **Phase 32: Advanced Citizen Search & Complaint Analytics** as planned.
