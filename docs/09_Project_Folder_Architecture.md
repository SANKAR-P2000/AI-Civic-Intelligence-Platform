# AI Civic Intelligence Platform (AICIP)

# Project Folder Architecture

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Version  | 1.0                                    |
| Document | Project Folder Architecture            |
| Status   | Planning                               |

---

# 1. Introduction

This document defines the folder and package structure for the AI Civic Intelligence Platform (AICIP). The project follows a layered architecture to ensure clean code, maintainability, scalability, and separation of concerns.

---

# 2. Overall Project Structure

```
AI-Civic-Intelligence-Platform/
│
├── backend/
├── frontend/
├── database/
├── docs/
├── diagrams/
├── assets/
└── README.md
```

---

# 3. Backend Structure (Spring Boot)

```
backend/
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── aicip/
        │           ├── config/
        │           ├── controller/
        │           ├── dto/
        │           ├── entity/
        │           ├── exception/
        │           ├── repository/
        │           ├── security/
        │           ├── service/
        │           │   └── impl/
        │           ├── util/
        │           └── AicipApplication.java
        │
        └── resources/
            ├── application.properties
            └── static/
```

---

# 4. Frontend Structure (React)

```
frontend/
└── src/
    ├── assets/
    ├── components/
    ├── layouts/
    ├── pages/
    ├── services/
    ├── hooks/
    ├── utils/
    ├── routes/
    ├── App.jsx
    └── main.jsx
```

---

# 5. Database Folder

```
database/
├── schema/
├── seed/
└── backups/
```

---

# 6. Documentation Folder

```
docs/
├── 01_Software_Requirements_Specification.md
├── 02_Use_Case_Analysis.md
├── 03_System_Architecture.md
├── 04_Database_Design.md
├── 05_Entity_Relationship_Diagram.md
├── 06_Database_Schema.md
├── 07_Table_Structure_Design.md
├── 08_API_Design.md
└── 09_Project_Folder_Architecture.md
```

---

# 7. Diagrams Folder

```
diagrams/
├── ER_Diagram.drawio
├── Use_Case_Diagram.drawio
├── System_Architecture.drawio
└── Class_Diagram.drawio
```

---

# 8. Assets Folder

```
assets/
├── screenshots/
├── icons/
└── logos/
```

---

# 9. Benefits

- Clean architecture
- Easy maintenance
- Scalable project structure
- Clear separation of frontend and backend
- Industry-standard organization

---

# 10. Summary

The AICIP project structure is organized to support professional full-stack development using React, Spring Boot, and MySQL while keeping documentation, database scripts, and assets well separated.

---

**End of Project Folder Architecture**
