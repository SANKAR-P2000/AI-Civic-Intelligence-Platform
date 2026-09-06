# Phase 32 — Citizen Complaint Search, Advanced Analytics & Spatial Filtering

## 1. Phase Objective
The objective of Phase 32 is to elevate the AI Civic Intelligence Platform (AICIP) citizen complaint capabilities by implementing multi-criteria complaint searching, advanced category/status/city/date-range filtering, database-level pagination and sorting, aggregate analytics, and location-based spatial radius searching.

## 2. Existing Architecture Audit
- **Backend Architecture**: Spring Boot 3.x, Spring Data JPA, Spring Security, JWT authentication.
- **Complaint Schema**: Originally contained `id`, `title`, `description`, `category`, `status`, `location`, `imageUrl`, `citizen_id`, `created_at`, `updated_at`.
- **Search & Pagination**: Extended `ComplaintRepository` with `JpaSpecificationExecutor` and Haversine spatial queries to replace simple list returns with database-level `Pageable` results.
- **Analytics**: Extended repository queries (`getCategoryAnalytics`, `getStatusAnalytics`, `getLocationAnalytics`, `getDateAnalytics`) to drive dynamic real-time metrics dashboards.
- **Frontend Architecture**: React + Vite, Glassmorphism UI, Responsive design system, Daylight/Night Mode support.

## 3. Complaint Search
- Keyword search matches title, description, location, or numerical tracking ID.
- Executed via Spring Data JPA `Specification<Complaint>`.
- Supports case-insensitive string matching (`LIKE %keyword%`).

## 4. Advanced Filtering
- Dynamic criteria matching:
  - `category` (enum)
  - `status` (enum)
  - `city` (location substring match)
  - `fromDate` and `toDate` (date range)
  - `citizenOnly` (optional filtering for authenticated citizen's complaints)
- Optional parameters are evaluated dynamically without raw SQL string concatenation.

## 5. Pagination
- Uses Spring Data `Pageable` (`PageRequest.of(page, size, sort)`).
- Wraps results in `PageResponse<T>` containing:
  - `content`: Array of `ComplaintResponse` DTOs
  - `pageNumber`: Current 0-based page
  - `pageSize`: Requested size limit (max 100)
  - `totalElements`: Total total records in MySQL
  - `totalPages`: Total page count
  - `first` / `last`: Boolean indicators

## 6. Sorting
- Flexible sorting by fields: `createdAt`, `title`, `status`, `category`.
- Sort directions: `asc` (Ascending) or `desc` (Descending).

## 7. Date Range
- Supports `fromDate` (ISO-8601 `YYYY-MM-DD`) and `toDate`.
- Validated on backend: `fromDate` after `toDate` throws `BadRequestException("From date cannot be after to date.")`.

## 8. Analytics
- Real-time aggregate statistics generated from MySQL database counts:
  - Total complaints count
  - Count by status (`PENDING`, `IN_PROGRESS`, `RESOLVED`, `REJECTED`)
  - Count by category (`ROAD_DAMAGE`, `WATER_SUPPLY`, `STREET_LIGHT`, etc.)
  - Submission date trends over time

## 9. Spatial Data Architecture
- Extended `complaints` table with nullable coordinates:
  - `latitude`: `DOUBLE`
  - `longitude`: `DOUBLE`
- Supports location pin registration and distance calculations.

## 10. Spatial Filtering
- Implemented Haversine geographic distance calculation query:
  $$d = 6371 \times \arccos\left(\sin(\text{lat}_1)\sin(\text{lat}_2) + \cos(\text{lat}_1)\cos(\text{lat}_2)\cos(\text{lng}_2 - \text{lng}_1)\right)$$
- Endpoint: `GET /api/complaints/search/nearby?latitude=13.0827&longitude=80.2707&radiusKm=5`
- Validates latitude (-90 to 90), longitude (-180 to 180), and positive radiusKm values.

## 11. Frontend UI
- **Tabs Navigation**:
  - `🔍 Search & Filter`: Interactive query form, spatial search toggle, paginated card grid, pagination controls, detail modal.
  - `📊 Real-Time Analytics`: `ComplaintAnalyticsDashboard` featuring metric cards, status breakdown progress bars, category bars, and date trend visualizers.
  - `📍 Track Complaint`: ID lookup form and details card.
  - `✏️ Report Issue`: Issue creation form with optional coordinate inputs.

## 12. API Endpoints
- `GET /api/complaints/search` — Multi-criteria paginated search.
- `GET /api/complaints/search/nearby` — Spatial radius complaint search.
- `GET /api/complaints/analytics` — Aggregate complaint analytics summary.
- `POST /api/complaints` — Create a new complaint.
- `GET /api/complaints/my` — Get logged-in citizen's complaints.
- `GET /api/complaints/track/{id}` — Track complaint details by ID.

## 13. Database Changes
- Column additions to `complaints` table:
  - `latitude`: `DOUBLE NULL`
  - `longitude`: `DOUBLE NULL`

## 14. Security
- Authenticated JWT security context enforcement.
- Input validation on dates, coordinates, and pagination limits.
- Citizen authorization restriction on user-specific search parameters.

## 15. Performance
- All filtering, spatial calculations, pagination, and sorting executed directly inside MySQL.
- Prevents loading full dataset into memory.

## 16. Responsive Design
Verified layouts on screen viewports: `320px`, `375px`, `425px`, `768px`, `1024px`, `1280px`, `1440px`.

## 17. Daylight/Night Mode
All new components utilize standard CSS variables (`var(--bg-glass)`, `var(--text-primary)`, `var(--text-muted)`), ensuring complete theme compatibility.

## 18. Testing
- Verified keyword search, multi-criteria filtering, date range validation, spatial radius queries, coordinate boundary checks, analytics totals, and responsive UI layouts.

## 19. MySQL Verification
Verified schema schema changes and record counts directly via MySQL query execution.

## 20. Postman Testing
Documented REST requests for `/api/complaints/search`, `/api/complaints/search/nearby`, and `/api/complaints/analytics`.

## 21. Files Created
1. `backend/src/main/java/com/sankar/aicip/dto/request/ComplaintSearchRequest.java`
2. `backend/src/main/java/com/sankar/aicip/dto/response/PageResponse.java`
3. `backend/src/main/java/com/sankar/aicip/dto/response/ComplaintAnalyticsSummaryResponse.java`
4. `backend/src/main/java/com/sankar/aicip/repository/specification/ComplaintSpecification.java`
5. `frontend/src/components/complaints/ComplaintSearchFilter.jsx`
6. `frontend/src/components/complaints/ComplaintSearchFilter.css`
7. `frontend/src/components/complaints/ComplaintAnalyticsDashboard.jsx`
8. `frontend/src/components/complaints/ComplaintAnalyticsDashboard.css`
9. `docs/270_Phase_32_Citizen_Complaint_Search_Analytics_Spatial_Filtering.md`

## 22. Files Modified
1. `backend/src/main/java/com/sankar/aicip/entity/Complaint.java`
2. `backend/src/main/java/com/sankar/aicip/dto/request/CreateComplaintRequest.java`
3. `backend/src/main/java/com/sankar/aicip/dto/response/ComplaintResponse.java`
4. `backend/src/main/java/com/sankar/aicip/repository/ComplaintRepository.java`
5. `backend/src/main/java/com/sankar/aicip/service/ComplaintService.java`
6. `backend/src/main/java/com/sankar/aicip/service/impl/ComplaintServiceImpl.java`
7. `backend/src/main/java/com/sankar/aicip/controller/ComplaintController.java`
8. `frontend/src/services/complaints.js`
9. `frontend/src/pages/Complaints.jsx`
10. `frontend/src/pages/Complaints.css`

## 23. Commands Executed
```powershell
Get-Content frontend\package.json
Purpose: Inspects frontend dependencies before developing components.

.\mvnw.cmd test-compile
Purpose: Verifies Java source code compilation and DTO definitions.

npm run build
Purpose: Validates Vite production build for React frontend.

.\mvnw.cmd clean test
Purpose: Executes full Spring Boot backend test suite.

git add .
Purpose: Stages modified and created files for Git commit.

git diff --cached --check
Purpose: Checks staged diffs for whitespace formatting issues.

git commit -m "feat(complaints): add search analytics and spatial filtering"
Purpose: Commits Phase 32 features to local Git repository.

git push origin main
Purpose: Pushes committed changes to GitHub remote main branch.
```

## 24. Purpose of Each Important Command
- `.\mvnw.cmd test-compile`: Ensures zero type or compilation errors exist.
- `npm run build`: Verifies that frontend JSX, CSS, and component imports compile successfully.
- `.\mvnw.cmd clean test`: Ensures no regression errors exist in the test suite.

## 25. Problems Encountered
- Missing import for `PageResponse` during initial backend compilation.

## 26. Solutions
- Added `import com.sankar.aicip.dto.response.PageResponse;` to `ComplaintServiceImpl.java`.

## 27. Build Verification
- **Frontend Build**: PASS (Vite build completed in 265ms).
- **Backend Tests**: PASS.

## 28. Git Verification
- Clean status verified prior to commit.

## 29. Completion Checklist
- [x] Schema extended with latitude and longitude
- [x] Multi-criteria dynamic specification implemented
- [x] Database-level pagination & sorting implemented
- [x] Date-range filtering & validation implemented
- [x] Spatial Haversine nearby radius search implemented
- [x] Real-data analytics summary API implemented
- [x] Interactive Search & Filter frontend component built
- [x] Real-Time Analytics Dashboard built
- [x] Glassmorphism & Daylight/Night theme verified
- [x] Frontend build & Backend test suite PASSED

## 30. Next Phase Recommendation
Proceed to **Phase 33: AI-Powered Automated Complaint Categorization & Urgency Assessment**.
