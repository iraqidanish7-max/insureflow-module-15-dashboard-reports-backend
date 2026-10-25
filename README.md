# Insurance PAS — Reporting Service (Module 15)

This is a standalone Spring Boot REST service for the Reports & Dashboard module. It uses the BRD reporting endpoint names and five reporting tables (INS054–INS058). Business data currently comes from an explicit **dummy data provider** because the other microservices' API contracts are not yet available. The schedules and execution history use MySQL.

## Requirements
- JDK 17 or later (project is configured for Java 17)
- IntelliJ IDEA
- Maven 3.9+ (or IntelliJ's Maven wrapper support)
- MySQL 8.x
- Internet access on first Maven import so dependencies can download

## 1. Open in IntelliJ
1. Extract the ZIP.
2. IntelliJ → File → Open → select this folder (the folder containing `pom.xml`).
3. Select JDK 17 for the project SDK and Maven importer.
4. Wait for Maven to download dependencies.

## 2. Create database
Run `database/01_reporting_schema.sql` in MySQL Workbench. It creates `pas_reporting`, the five reporting tables, and the report/widget seed data. The application is configured with `spring.jpa.hibernate.ddl-auto=none`, so SQL must be run first.

## 3. Configure credentials
The defaults in `src/main/resources/application.properties` are for local MySQL with username `root` and an empty password. If your MySQL credentials differ, edit the defaults or set environment variables:

- `DB_URL=jdbc:mysql://localhost:3306/pas_reporting?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Kolkata`
- `DB_USERNAME=your_mysql_username`
- `DB_PASSWORD=your_mysql_password`
- `SERVER_PORT=8087` (optional)

Never commit a real database password to Git.

## 4. Build and run
From the project root:

```bash
mvn clean test
mvn spring-boot:run
```

Or run `ReportingServiceApplication.java` from IntelliJ. A successful start should log that Tomcat started on port 8087 and Hibernate connected to `pas_reporting`.

## 5. Swagger
Open `http://localhost:8087/swagger-ui.html` or `http://localhost:8087/v3/api-docs`.

## 6. First Postman tests
Base URL: `http://localhost:8087`

| Method | URL | Expected |
|---|---|---|
| GET | `/api/v1/health` | Service UP |
| GET | `/api/v1/dashboard/summary` | KPI summary; `dataSource` is `DUMMY_DATA` |
| GET | `/api/v1/dashboard/kpi` | KPI summary |
| GET | `/api/v1/dashboard/charts` | Chart-ready lists |
| GET | `/api/v1/reports/customers` | Sample customer report |
| GET | `/api/v1/reports/customers?branch=Mumbai%20Central` | Filters sample customer rows by branch |
| GET | `/api/v1/reports/policies?status=Active` | Active sample policies |
| GET | `/api/v1/reports/premiums?status=SUCCESS` | Successful sample payments |
| GET | `/api/v1/reports/claims?status=SETTLED` | Settled sample claims |
| GET | `/api/v1/reports/underwriting?status=PENDING` | Pending sample cases |
| GET | `/api/v1/reports/agents` | Sample agent metrics |
| GET | `/api/v1/reports/financial` | Sample financial records |
| GET | `/api/v1/reports/export/csv?category=policies` | Downloads CSV |
| GET | `/api/v1/reports/export/excel?category=customers` | Downloads XLSX |
| GET | `/api/v1/reports/export/pdf?category=claims` | Downloads PDF |
| GET | `/api/v1/reports/schedule` | Current schedule rows |
| GET | `/api/v1/reports/executions` | Recent report execution history |

### Create a report schedule
`POST http://localhost:8087/api/v1/reports/schedule`

Body → raw → JSON:

```json
{
  "reportId": 1,
  "scheduleType": "Daily",
  "nextRunDate": "2026-10-21T09:00:00",
  "emailTo": "demo@example.com"
}
```

Supported `scheduleType`: `Daily`, `Weekly`, `Monthly`, `Quarterly`, `Yearly`.

## Important scope notes
- This milestone intentionally uses dummy business data for customer/policy/premium/claims/underwriting/agent reports. It does not read other microservices' databases.
- `report_schedule` saves configuration only. It does **not** yet execute schedules or send email/FTP files automatically.
- Export endpoints create CSV, XLSX and PDF files, but large/background exports and download-center workflow are not implemented yet.
- The BRD requires JWT/RBAC, report-level permissions, masking, and audit logs. Connect the service to the team's agreed authentication/gateway before exposing it outside local development. This initial local build does not claim to enforce those permissions.
- The BRD defines the loss ratio as claims divided by premium × 100 but does not fully specify the amount basis. The sample KPI uses settled claim amount divided by successful collected premium; treat it as demo logic until the business team confirms the definition.
- Date, city, state, product, agent, and branch filters are listed in the BRD. This first version implements `from`, `to`, `status`, `branch`, and `product` for relevant report endpoints; extend city/state/agent filters after the API contract is agreed.
- `report_execution.executed_by` remains nullable because the team's authenticated user identity contract is not yet known.

## API response format
Most JSON endpoints return:

```json
{
  "success": true,
  "message": "...",
  "data": {},
  "timestamp": "..."
}
```

## Gateway and service registry
This project runs standalone on port 8087 first. After local testing, coordinate the gateway route and service-registry settings with the team's supplied projects. Do not copy gateway or registry code into this service.
