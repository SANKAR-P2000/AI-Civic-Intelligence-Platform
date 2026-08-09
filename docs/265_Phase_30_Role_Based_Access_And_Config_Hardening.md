# Phase 30 – Role-Based Access Polish & Production Config Hardening

## Status

- Phase 29.9 – Browser Flow Verification & Next Phase Readiness: ✅ Completed
- Phase 30 – Role-Based Access Polish & Production Config Hardening: ✅ Completed
- Phase 31 – Next Phase (recommended items below): Ready to start

---

# 1. Objective

The integration baseline (frontend ↔ Spring Boot backend ↔ MySQL) is verified
through the browser. Phase 30 hardens the application for both **usability**
(role-aware navigation) and **production safety** (no committed secrets).

---

# 2. What Changed

## 2.1 Role-Aware Footer (`frontend/src/components/layout/Footer.jsx`)

The footer now adapts to the current session:

- **Guests:** see Report a Complaint, Track Complaint, Services (no Dashboard).
- **Authenticated citizens:** see Dashboard + "My Dashboard".
- **Admins:** Dashboard link routes to `/admin/complaints` and shows
  "My Admin".

This makes the footer consistent with the Navbar (which already shows admin
links only for ADMIN users) and prevents dead links for guests.

## 2.2 Production Config Hardening (`backend/src/main/resources/application.properties`)

Hardcoded secrets were replaced with **environment-variable placeholders** that
fall back to safe, non-secret defaults. Dev values remain in
`application-local.properties` (git-ignored real credentials).

| Property               | Before (committed)         | After (env override)                          |
| ---------------------- | -------------------------- | --------------------------------------------- |
| `jwt.secret`           | `your_jwt_secret_key`      | `${JWT_SECRET:your_jwt_secret_key_change_me}` |
| `jwt.expiration`       | `86400000`                 | `${JWT_EXPIRATION:86400000}`                  |
| `file.upload-dir`      | `uploads/complaint-images` | `${FILE_UPLOAD_DIR:uploads/complaint-images}` |
| `spring.mail.username` | `YOUR_GMAIL@gmail.com`     | `${MAIL_USERNAME:}`                           |
| `spring.mail.password` | `YOUR_APP_PASSWORD`        | `${MAIL_PASSWORD:}`                           |

Because `spring.profiles.active=local` is set in `application.properties`, the
**`local` profile still overrides** these with the real credentials from
`application-local.properties` during development — so nothing breaks.

---

# 3. Verification

- Frontend `npm run build` — **passed** ✅
- Backend `.\mvnw -q compile` — **passed** ✅
- Vite HMR applied `Footer.jsx` live (`hmr update /src/components/layout/Footer.jsx`) ✅

---

# 4. Recommended Next Phase (Phase 31) Work Items

- **AI Analytics UI** — flesh out the analytics dashboard with charts (currently
  CSS bar charts; could add a charting library).
- **Citizen complaint search** — add search/filter for a citizen's own
  complaints in `Dashboard`.
- **Email notifications** — confirm SMTP works with the env-configured
  credentials (currently `application-local.properties` has the real Gmail app
  password).
- **End-to-end Postman collection** — full citizen + admin flows.
- **CI/CD** — GitHub Actions build for backend (Maven) + frontend (Vite).

---

# 5. Files Added / Changed

- Modified: `frontend/src/components/layout/Footer.jsx` — role-aware links.
- Modified: `backend/src/main/resources/application.properties` — env-var
  secrets with safe defaults.
- Added: `docs/265_Phase_30_Role_Based_Access_And_Config_Hardening.md`.
- Updated: `TODO.md` — Phase 30 marked complete.
