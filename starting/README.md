# TaskFlow: Spring Boot REST API

A production-ready RESTful Web API built with Spring Boot. This project demonstrates enterprise-level backend architecture, strict data validation, and persistent database storage using modern Java paradigms.

## 🚀 The Elevator Pitch
This isn't just a basic CRUD app; it's built to mimic real-world enterprise standards. It implements a strict 3-tier architecture separating network routing, business logic, and database operations. It includes global error handling to prevent server crashes and ensures only sanitized, valid data reaches the database.

## 🛠️ Tech Stack
* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Web Layer:** Spring Web (RESTful routing, Tomcat)
* **Data Layer:** Spring Data JPA, Hibernate
* **Database:** H2 In-Memory Database (easily swappable to PostgreSQL/MySQL)
* **Build Tool:** Maven

## 🧠 Core Architecture & Features
* **N-Tier Architecture:** Complete decoupling of `Controllers` (HTTP handling), `Services` (Business Logic), and `Repositories` (Data Access).
* **Robust Data Validation:** Utilizes Jakarta Validation (`@Valid`, `@NotBlank`, `@Size`) to intercept and bounce malformed JSON payloads before processing.
* **Global Exception Handling:** A `@RestControllerAdvice` safety net that intercepts internal Java exceptions (like `TaskNotFoundException` or validation failures) and translates them into clean, standardized HTTP 400 and 404 JSON responses.
* **Dynamic Database Querying:** Uses Spring Data JPA derived query methods (e.g., `findByDescriptionContainingIgnoreCase`) to execute optimized, case-insensitive partial text searches without writing raw SQL.

## 📡 API Endpoints Reference

| HTTP Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/tasks` | Retrieve all tasks |
| `GET` | `/tasks?search={word}` | Filter tasks by a partial, case-insensitive string |
| `GET` | `/tasks?completed={bool}`| Filter tasks by their completion status |
| `GET` | `/tasks/{id}` | Retrieve a specific task by its ID |
| `POST` | `/tasks` | Create a new task (requires valid JSON body) |
| `PUT` | `/tasks/{id}` | Update an existing task |
| `DELETE` | `/tasks/{id}` | Delete a task from the database |

## 💻 How to Run Locally

1. **Clone the repository:**
   ```bash
   git clone [git@github.com:Delyassss/TaskFlow-Spring-Boot-REST-API.git](git@github.com:Delyassss/TaskFlow-Spring-Boot-REST-API.git)
   cd TaskFlow-REST-API
   Run the server using the Maven Wrapper (no local Maven install required):


2. Boot the server:

For Linux / macOS:

```Bash  
./mvnw spring-boot:run
```
For Windows (Command Prompt / PowerShell):
```
mvnw.cmd spring-boot:run

The embedded Tomcat server will automatically start on http://localhost:8080.
```
3. Verify it works:
   Open a new terminal and send a test request to ensure the database and validation are running:

```Bash
curl -X GET http://localhost:8080/tasks