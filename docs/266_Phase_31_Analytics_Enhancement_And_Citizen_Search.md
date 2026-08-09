# Phase 31 – Analytics Enhancement & Citizen Dashboard Search

## Status

- Phase 30 – Role-Based Access Polish & Production Config Hardening: ✅ Completed
- Phase 31 – Analytics Enhancement & Citizen Dashboard Search: ✅ Completed
- Phase 32 – Next Phase (recommended items below): Ready to start

---

# 1. Objective

Enhance the admin analytics and citizen dashboard with two concrete, real-time
features that improve usability:

1. **"By Date" analytics chart** — the backend already exposes
   `GET /api/admin/analytics/date`, but the frontend never rendered it. Wire it
   up and show complaint counts per day.
2. **Citizen complaint search** — let users filter their own complaints on the
   dashboard by title, description, location, category, or status.

---

# 2. What Changed

## 2.1 "By Date" Analytics Chart (`frontend/src/pages/AdminAnalytics.jsx`)

- Added a `date` state and fetched it via the existing
  `analyticsService.getDateAnalytics()` alongside the other three datasets.
- Added a `formatDate()` helper to turn the backend MySQL `DATE` string
  (`2026-08-02`) into a readable locale date (`Aug 2, 2026`).
- Extended `BarRow` and `ChartCard` to accept an optional `formatter` prop so
  the date chart can reuse the same bar rendering while formatting labels.
- Rendered a new `"By Date"` chart card in the analytics grid.

## 2.2 Citizen Complaint Search (`frontend/src/pages/Dashboard.jsx`)

- Added a `search` state and a search `Input` above the complaint grid.
- Added a `useMemo`-based `filteredComplaints` that matches the query against a
  complaint's title, description, location, category, and status
  (case-insensitive).
- Moved the `useMemo` call **above** the early `return` for loading to satisfy
  the React hooks rules-of-hooks.
- Shows a "No complaints match your search" empty state when the filter yields
  nothing.
- The search bar only appears when there are complaints to search.

## 2.3 CSS (`frontend/src/pages/Dashboard.css`)

- Added `.dashboard__search` styling — a constrained-width search field with
  consistent spacing below the section heading.

---

# 3. Verification

- Frontend `npm run build` — **passed** ✅
- Frontend `npm run lint` — **passed** ✅ (no errors; `useMemo`/`Input`/`search`
  all used, hooks order correct)
- Vite HMR applied both `AdminAnalytics.jsx` and `Dashboard.jsx`/`.css` live ✅

---

# 4. Recommended Next Phase (Phase 32) Work Items

- **Charting library** — replace the CSS bar charts with a real chart library
  (e.g. Recharts) for richer, interactive visualizations and tooltips.
- **Email notifications verification** — confirm SMTP works with the
  env-configured credentials.
- **End-to-end Postman collection** — full citizen + admin flows documented.
- **CI/CD** — GitHub Actions workflow to build backend (Maven) + frontend
  (Vite) on push.
- **Pagination** — add pagination to admin complaint lists once volume grows.

---

# 5. Files Added / Changed

- Modified: `frontend/src/pages/AdminAnalytics.jsx` — added "By Date" chart.
- Modified: `frontend/src/pages/Dashboard.jsx` — added citizen search/filter.
- Modified: `frontend/src/pages/Dashboard.css` — search bar styling.
- Added: `docs/266_Phase_31_Analytics_Enhancement_And_Citizen_Search.md`.
- Updated: `TODO.md` — Phase 31 marked complete.
