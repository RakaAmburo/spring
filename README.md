# 🍃 Spring Boot Modern Showcase (work in progress)

> A multi-module Spring Boot reference project demonstrating modern enterprise patterns and best practices.

This repository is a living showcase of the most relevant features and architectural patterns in the Spring Boot ecosystem — from service discovery to reactive programming, from JWT security to distributed tracing. Each module is self-contained and documented.

---

## 🏗️ Architecture Overview

```
spring-modern-showcase/
├── config-server/          # Centralized configuration (Spring Cloud Config)
├── eureka-server/          # Service discovery (Netflix Eureka)
├── api-gateway/            # API Gateway (Spring Cloud Gateway)
├── auth-service/           # JWT Authentication & Authorization
├── jpa-module/             # Relational data with Spring Data JPA
├── mongo-module/           # Document store with Spring Data MongoDB
└── commons/                # Shared DTOs, utilities, exceptions
```

---

## 🧩 Modules

### ⚙️ Config Server
Centralized external configuration for all services using **Spring Cloud Config**.
- Git-backed configuration
- Environment-specific profiles (`dev`, `prod`)
- Encrypted secrets support

### 🔭 Eureka Server
Service registry and discovery using **Netflix Eureka**.
- Auto-registration of all microservices
- Health check dashboard at `/eureka`

### 🔐 Auth Service
Stateless authentication using **JWT**.
- Login / refresh token flow
- Role-based access control (`ROLE_USER`, `ROLE_ADMIN`)
- Spring Security 6 with `SecurityFilterChain`
- Password hashing with BCrypt

### 🗄️ JPA Module
Relational data access with **Spring Data JPA** + PostgreSQL.
- Entity relationships (`@OneToMany`, `@ManyToMany`)
- Custom JPQL queries
- Pagination and sorting
- Flyway migrations

### 🍃 Mongo Module
Document-oriented data access with **Spring Data MongoDB**.
- `@Document` entities
- Aggregation pipelines
- Reactive support with `ReactiveMongoRepository`

### 🌐 API Gateway
Single entry point for all services using **Spring Cloud Gateway**.
- Route configuration
- JWT validation filter
- Rate limiting

---

## 🛠️ Tech Stack

| Category | Technology |
|----------|-----------|
| Framework | Spring Boot 3.x |
| Security | Spring Security 6 + JWT |
| Service Discovery | Netflix Eureka |
| Config | Spring Cloud Config |
| Gateway | Spring Cloud Gateway |
| ORM | Spring Data JPA (Hibernate) |
| NoSQL | Spring Data MongoDB |
| Database | PostgreSQL + MongoDB |
| Migrations | Flyway |
| Build | Maven (multi-module) |
| Containerization | Docker + Docker Compose |
| Documentation | SpringDoc OpenAPI (Swagger) |

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Gradle 3.9+
- Docker & Docker Compose

### Run with Docker Compose

```bash
git clone https://github.com/youruser/spring-modern-showcase.git
cd spring-modern-showcase
docker-compose up -d
```

### Run manually

```bash
# 1. Start Config Server
cd config-server && mvn spring-boot:run

# 2. Start Eureka Server
cd eureka-server && mvn spring-boot:run

# 3. Start any module
cd jpa-module && mvn spring-boot:run
```

---

## 🔐 Authentication Flow

```
POST /auth/login
  → validates credentials
  → returns access_token (15min) + refresh_token (7d)

POST /auth/refresh
  → validates refresh_token
  → returns new access_token

Authorization: Bearer <access_token>
  → validated on every protected request via filter chain
```

---

## 📖 API Documentation

Each module exposes Swagger UI at:
```
http://localhost:{port}/swagger-ui.html
```

| Service | Port |
|---------|------|
| Config Server | 8888 |
| Eureka Server | 8761 |
| Auth Service | 8080 |
| JPA Module | 8081 |
| Mongo Module | 8082 |
| API Gateway | 9000 |

---

## 🗺️ Roadmap

- [ ] Distributed tracing (Micrometer + Zipkin)
- [ ] Event-driven messaging (Kafka)
- [ ] Reactive streams (WebFlux)
- [ ] Caching (Redis)
- [ ] Circuit breaker (Resilience4j)
- [ ] Observability (Prometheus + Grafana)
- [ ] gRPC module

---

## 📄 License

MIT
