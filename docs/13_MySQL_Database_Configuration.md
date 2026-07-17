# AI Civic Intelligence Platform (AICIP)

# MySQL Database Configuration

---

## Document Information

| Item     | Details                                |
| -------- | -------------------------------------- |
| Project  | AI Civic Intelligence Platform (AICIP) |
| Document | MySQL Database Configuration           |
| Version  | 1.0                                    |
| Status   | Completed                              |

---

# 1. Objective

Configure the Spring Boot backend to communicate with the MySQL database.

---

# 2. Database Information

| Property      | Value                  |
| ------------- | ---------------------- |
| Database Name | aicip_db               |
| Database Type | MySQL Community Server |
| Version       | 8.0.44                 |

---

# 3. Database Creation

```sql
CREATE DATABASE aicip_db;
```

Verify:

```sql
SHOW DATABASES;
```

Expected Result:

```
aicip_db
```

---

# 4. Spring Boot Configuration

File Location:

```
backend/src/main/resources/application.properties
```

Required Properties:

```properties
spring.application.name=backend

spring.datasource.url=jdbc:mysql://localhost:3306/aicip_db
spring.datasource.username=root
spring.datasource.password=********

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

> Never store your real database password in public documentation or screenshots.

---

# 5. Purpose of Each Property

## spring.datasource.url

Specifies the database server and database name.

---

## spring.datasource.username

Database username used for authentication.

---

## spring.datasource.password

Database password used for authentication.

---

## spring.datasource.driver-class-name

Specifies the MySQL JDBC driver.

---

## spring.jpa.hibernate.ddl-auto

Automatically updates the database schema during development.

---

## spring.jpa.show-sql

Displays generated SQL statements in the console.

---

## spring.jpa.properties.hibernate.format_sql

Formats SQL statements for readability.

---

## server.port

Defines the HTTP port used by the Spring Boot application.

---

# 6. Verification Checklist

- Database created successfully.
- Spring Boot configured with MySQL.
- MySQL JDBC driver available.
- Ready for entity creation.

---

# 7. Next Step

Run the application to verify the database connection.

---

**End of MySQL Database Configuration**
