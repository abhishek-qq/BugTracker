# 🐞 Bug Tracker API

A fully-tested, secure, and production-grade **Bug Tracker REST API** built with **Spring Boot**, **JWT Authentication**, and **JUnit/Mockito**. Designed with best practices, clean architecture, and ready for real-world deployment.

> 🔐 Features: Authentication · Authorization · Validation · Exception Handling · Full Test Coverage · DTO Mapping

---

## 🚀 Tech Stack

| Layer         | Technology                       |
|---------------|----------------------------------|
| Backend       | Spring Boot (Java 17+)           |
| Security      | Spring Security + JWT            |
| Database      | MySQL / H2 (Dev/Test)            |
| Testing       | JUnit 5 · Mockito · MockMvc      |
| Build Tool    | Maven                            |
| Validation    | Jakarta Bean Validation (JSR-380)|
| Utilities     | Lombok · MapStruct-style Mapper  |

---

## 📂 Project Structure

```
src/
 ├── controller/        # REST APIs (Bug & Auth)
 ├── service/           # Business logic
 ├── entity/            # JPA entities (User, Bug)
 ├── dto/               # Request & response models
 ├── repository/        # Spring Data JPA interfaces
 ├── security/          # JWT token utils + filters
 ├── exception/         # Global exception handler
 └── test/              # Unit & integration tests
```

---

## 🔐 Authentication Flow (JWT)

| Endpoint               | Description            |
|------------------------|------------------------|
| `POST /api/auth/register` | Register new user    |
| `POST /api/auth/login`    | Login and receive token |
| `Authorization` Header    | Use: `Bearer <token>` |

#### 🔑 Example Token Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

---

## 🐞 Bug Management Endpoints

| Method | Endpoint             | Secured | Description         |
|--------|----------------------|---------|---------------------|
| POST   | `/api/bugs`          | ✅      | Create new bug      |
| GET    | `/api/bugs`          | ✅      | List all bugs       |
| GET    | `/api/bugs/{id}`     | ✅      | Get bug by ID       |
| GET    | `/api/bugs/status/{status}` | ✅ | Filter by status   |
| PUT    | `/api/bugs/{id}`     | ✅      | Update bug          |
| DELETE | `/api/bugs/{id}`     | ✅      | Delete bug          |

---

## 🧪 Testing Strategy

| Layer       | Toolset               | Coverage         |
|-------------|------------------------|------------------|
| Unit Tests  | JUnit 5 + Mockito      | Services, JWT    |
| Web Layer   | Spring `@WebMvcTest`   | Controllers      |
| Security    | `MockMvc` + JWT tokens | Auth flows       |
| Integration| `@SpringBootTest`       | End-to-end flows |

```bash
# Run all tests
mvn clean test
```

---

## 💡 Sample `.application.properties`

```properties
# Database Config
spring.datasource.url=jdbc:mysql://localhost:3306/bugtracker
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

# JWT Secret (minimum 256-bit)
jwt.secret=mysecretkeyforjwtauthenticationmysecretkeyforjwtauthentication
```


## ✨ Highlights

- 🔐 Secure APIs with token-based access
- 🧪 End-to-end testing with 90–100% test coverage
- 🧼 Clean code, well-structured DTO layers
- 💣 Handles validation & global error responses
- 🧩 Ready to integrate with frontends or microservices

---

## 🧠 Learning Outcomes

✔️ Spring Boot Architecture  
✔️ Spring Security & JWT  
✔️ Test-Driven Development  
✔️ DTO Design + Mapping  
✔️ Clean Exception Handling  
✔️ Mocking & Integration Testing  

---

## 👨‍💻 Author

**Abhishek Mishra**  
Senior Software Engineer  
📌 `Java | Spring Boot | Microservices | Unit Testing | Secure API Design`
