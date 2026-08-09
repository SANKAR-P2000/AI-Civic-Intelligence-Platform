# AICIP Integration & Bug Fixes - Implementation TODO

## Goal

Connect backend, frontend, and database in real-time; fix integration bugs in the full Spring Boot project; and document every update in docs/ following the current order.

## Status: 🔄 IN PROGRESS

## Steps

### 1. Backend Integration Fixes - ✅

- [x] Fix `SecurityConfig.java` — permit `/uploads/**` and public `/api/complaints/track/**`
- [x] Add citizen dashboard stats endpoint (`GET /api/dashboard/mystats`)
- [x] Activate local profile (`spring.profiles.active=local`) for real DB connection
- [x] Make public complaint tracking work (removed auth requirement on track)

### 2. Frontend Integration Fixes - ✅

- [x] Update `Dashboard.jsx` to use citizen stats endpoint for citizens
- [x] Add `getMyStats()` to complaints service
- [x] Add `VITE_API_BASE_URL` documentation to `.env.example`

### 3. Verification - ✅

- [x] Run `npm run build` (frontend) — passed
- [x] Run `npm run lint` (frontend) — passed
- [x] Run `mvn compile` (backend) — passed
- [x] Run `mvn test-compile` (backend) — passed

### 4. Documentation (docs/) - ✅

- [x] `258_Phase_29.5_Backend_Frontend_Integration.md`
- [x] `259_Phase_29.5.1_Integration_Bug_Fixes.md`
- [x] `260_Phase_29.5.2_System_Integration_Architecture.md`
- [x] Update `TODO.md`

### 5. Git Commit - ✅

- [x] Commit with conventional message: `feat: integrate frontend, backend, and database; fix integration bugs`

### 6. Phase 29.6 – Admin UI, File Upload & Bug Fixes - ✅

- [x] Add `services/upload.js` — multipart image upload with JWT
- [x] Add `services/analytics.js` — admin analytics client
- [x] Add `uploadComplaintImage` to complaint submission form (photo + preview)
- [x] Add `AdminComplaints.jsx` — search, filter, inline status update
- [x] Add `AdminAnalytics.jsx` — CSS bar charts by category/status/location
- [x] Register `/admin/complaints` and `/admin/analytics` routes (ADMIN role)
- [x] Show admin nav links only for ADMIN role
- [x] Fix `ComplaintServiceImpl` to return relative `/uploads/...` image URLs
- [x] Document `docs/261_Phase_29.6_Admin_UI_File_Upload_And_Bug_Fixes.md`
- [x] Frontend `npm run build` — passed
- [x] Frontend ESLint — passed
- [x] Backend `mvnw compile` — passed

### 7. Phase 29.7 – "Network Error" / Port 8080 Conflict Fix - ✅

- [x] Diagnosed stale `java.exe` (PID 21068) holding port 8080
- [x] Terminated stale process with `taskkill /PID 21068 /F`
- [x] Restarted backend with latest code via `mvnw spring-boot:run -Dspring-boot.run.profiles=local` (PID 7192)
- [x] Verified frontend `:5173 → 200`, backend `:8080 → 401` (bad creds), proxy chain works
- [x] Verified full register→login flow via proxy → `201` + `200` with valid JWT tokens
- [x] Confirmed backend PID on :8080 is a current `java.exe` (JDK 25) running latest code
- [x] Frontend `npm run build` — passed
- [x] Document `docs/262_Phase_29.7_Network_Error_Port_Conflict_Fix.md`

### 8. Phase 29.8 – Daylight Mode Form Visibility Fix - ✅

- [x] Fix `Input.css` — labels, required `*`, error, helper now use theme-aware `--theme-*` tokens
- [x] Both Sign In & Create Account labels visible in daylight + night mode
- [x] Also fixes Complaints form + Admin search labels (shared `aicip-field` classes)
- [x] Frontend `npm run build` — passed
- [x] Document `docs/263_Phase_29.8_Daylight_Form_Visibility_Fix.md` (with localhost usage guide)
