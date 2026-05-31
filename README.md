# 🛒 E-Commerce Microservices Architecture

A production-ready E-Commerce Microservices project built using Java 17, Spring Boot, Spring Cloud, Eureka Service Discovery, API Gateway, Kafka, Redis, MySQL, MongoDB, and Docker.

---

# 📌 Project Overview

This project demonstrates a scalable distributed microservices architecture used in modern e-commerce and fintech systems.

The application is divided into multiple independent services communicating through REST APIs and event-driven messaging.

---

# 🏗️ Architecture Diagram

![Architecture Diagram](a_wide_infographic_architecture_diagram_with_a_cle.png)

---

# 🚀 Tech Stack

| Technology   | Purpose                    |
| ------------ | -------------------------- |
| Java 17      | Backend Development        |
| Spring Boot  | Microservices Framework    |
| Spring Cloud | Distributed System Support |
| Eureka       | Service Discovery          |
| API Gateway  | Centralized Routing        |
| MySQL        | Relational Database        |
| MongoDB      | Product Catalog Database   |
| Kafka        | Event Streaming            |
| Redis        | Caching                    |
| Docker       | Containerization           |
| Maven        | Build Tool                 |

---

# 📂 Project Structure

```text
ecommerce-microservices/
│
├── api-gateway/
├── service-discovery/
├── user-service/
├── product-service/
├── order-service/
├── payment-service/
├── notification-service/
├── common-lib/
├── pom.xml
└── README.md
```

---

# ⚙️ Microservices

| Service              | Description             | Port |
| -------------------- | ----------------------- | ---- |
| API Gateway          | Single Entry Point      | 8080 |
| Eureka Server        | Service Discovery       | 8761 |
| User Service         | User Management         | 8081 |
| Product Service      | Product Catalog         | 8082 |
| Order Service        | Order Processing        | 8083 |
| Payment Service      | Payment Handling        | 8084 |
| Notification Service | Email/SMS Notifications | 8085 |

---

# 🌐 System Architecture

## Request Flow

```text
Client
   ↓
API Gateway
   ↓
Microservices
   ↓
Database / Kafka / Redis
```

---

# 🔥 Features

* Microservices Architecture
* API Gateway Routing
* Service Discovery
* Distributed Communication
* Event-Driven Architecture
* Redis Caching
* Kafka Integration
* Docker Support
* Scalable Design
* Cloud Ready

---

# 🧩 Services Included

## 👤 User Service

Handles:

* User Registration
* Authentication
* User Management

---

## 📦 Product Service

Handles:

* Product Catalog
* Product Inventory
* Product Search

---

## 🛒 Order Service

Handles:

* Order Creation
* Order Tracking
* Order Workflow

---

## 💳 Payment Service

Handles:

* Payment Processing
* Transaction Management
* Payment Validation

---

## 🔔 Notification Service

Handles:

* Email Notifications
* SMS Notifications
* Event Notifications

---

# ⚡ Kafka Event Flow

```text
Order Created
      ↓
Kafka Event
      ↓
Payment Service
      ↓
Notification Service
```

---

# 🗄️ Database Design

| Service         | Database |
| --------------- | -------- |
| User Service    | MySQL    |
| Product Service | MongoDB  |
| Order Service   | MySQL    |
| Payment Service | Redis    |

---

# 🐳 Docker Support

## Dockerfile

```dockerfile
FROM openjdk:17

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
```

---

# 🛠️ Installation

## Clone Repository

```bash
git clone https://github.com/your-username/ecommerce-microservices.git
```

---

# ▶️ Run the Project

## Build Project

```bash
mvn clean install
```

---

## Run Eureka Server

```bash
cd service-discovery
mvn spring-boot:run
```

---

## Run User Service

```bash
cd user-service
mvn spring-boot:run
```

---

## Run Product Service

```bash
cd product-service
mvn spring-boot:run
```

---

## Run API Gateway

```bash
cd api-gateway
mvn spring-boot:run
```

---

# 🌍 Service URLs

| Service              | URL                                            |
| -------------------- | ---------------------------------------------- |
| Eureka Dashboard     | [http://localhost:8761](http://localhost:8761) |
| API Gateway          | [http://localhost:8080](http://localhost:8080) |
| User Service         | [http://localhost:8081](http://localhost:8081) |
| Product Service      | [http://localhost:8082](http://localhost:8082) |
| Order Service        | [http://localhost:8083](http://localhost:8083) |
| Payment Service      | [http://localhost:8084](http://localhost:8084) |
| Notification Service | [http://localhost:8085](http://localhost:8085) |

---

# 📊 High-Level Architecture

## Microservices Communication

```text
API Gateway
     ↓
Eureka Discovery
     ↓
User Service
Product Service
Order Service
Payment Service
Notification Service
```

---

# ☁️ Production Enhancements

Future improvements:

* Kubernetes Deployment
* CI/CD Pipeline
* JWT Authentication
* Prometheus Monitoring
* Grafana Dashboard
* ELK Logging
* Zipkin Distributed Tracing

---

# 🎯 Learning Objectives

This project helps developers learn:

* Spring Boot Microservices
* Distributed Systems
* API Gateway Pattern
* Service Discovery
* Event-Driven Architecture
* Kafka Messaging
* Redis Caching
* Docker Deployment

---

# 🤝 Contributing

Pull requests are welcome.

For major changes, please open an issue first to discuss the proposed updates.

---

# 📜 License

This project is licensed under the MIT License.

---

# 👨‍💻 Author

Moni Shanker

* Java Developer
* Spring Boot Developer
* Microservices Enthusiast

---
