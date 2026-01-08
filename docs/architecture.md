# Smart E-Commerce System Architecture

## Overview

The Smart E-Commerce System follows a modular, layered architecture built on Spring Boot 3.x with Java 21. The system implements clean architecture principles with separation of concerns between presentation, business logic, and data access layers.

## High-Level Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                        Presentation Layer                   │
├─────────────────────────────────────────────────────────────┤
│  REST Controllers  │  GraphQL Controllers  │  GraphQL Schema │
├─────────────────────────────────────────────────────────────┤
│                     Service Layer                           │
│  Business Logic  │  Validation  │  Cross-cutting Concerns   │
├─────────────────────────────────────────────────────────────┤
│                    Data Access Layer                        │
│    JPA Repositories  │  Database Entities  │  Specifications │
├─────────────────────────────────────────────────────────────┤
│                      Database Layer                         │
│                    Relational Database                      │
└─────────────────────────────────────────────────────────────┘
```

## Module Structure

### Core Modules

| Module | Responsibility |
|--------|----------------|
| `auth` | Authentication and authorization services |
| `user` | User management and profile services |
| `product` | Product catalog and management |
| `category` | Product categorization system |
| `cart` | Shopping cart functionality |
| `order` | Order processing and management |
| `payment` | Payment processing integration |
| `inventory` | Stock management and tracking |
| `recommendation` | Product recommendation engine |
| `notification` | Notification services (email, SMS) |
| `analytics` | Analytics and reporting |

### Supporting Packages

| Package | Purpose |
|---------|---------|
| `config` | Application configuration and beans |
| `common` | Shared utilities and base classes |
| `aop` | Aspect-oriented programming implementations |
| `security` | Security configuration and authentication |
| `integration` | External service integrations |

## Layer Architecture

### 1. Controller Layer
- REST endpoints following RESTful conventions
- GraphQL resolvers for flexible data queries
- Request validation and response formatting
- Exception handling delegation

### 2. Service Layer
- Business logic implementation
- Transaction management
- Cross-module coordination
- Algorithmic operations (sorting, searching, pagination)

### 3. Repository Layer
- Data access operations
- JPA specifications for dynamic queries
- Database abstraction
- Query optimization

### 4. Entity Layer
- Domain models mapping to database tables
- JPA annotations for ORM
- Validation constraints
- Auditing information

## Design Patterns Implemented

### Dependency Injection
- Constructor-based injection throughout the application
- Spring-managed beans
- Inversion of control principles

### DTO Pattern
- Separate data transfer objects for API communication
- Validation at DTO level
- Mapping between entities and DTOs

### Repository Pattern
- Abstraction over data access logic
- JPA repositories with custom methods
- Specification pattern for dynamic queries

### AOP Implementation
- Cross-cutting concerns (logging, security, performance)
- Method execution time tracking
- Audit logging

## Technology Stack

| Layer | Technology |
|-------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3.x |
| Web | Spring Web, Spring GraphQL |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL/H2 |
| Security | Spring Security, JWT |
| Documentation | Springdoc OpenAPI |
| Build | Maven |

## API Architecture

### REST API Design
- Resource-based endpoints
- Standard HTTP methods (GET, POST, PUT, DELETE)
- Consistent response format
- Pagination, filtering, and sorting support

### GraphQL API Design
- Schema-first approach
- Type definitions for all entities
- Query and mutation operations
- Efficient data fetching with field selection

## Configuration Management

### Profiles
- `dev`: Development environment
- `test`: Testing environment  
- `prod`: Production environment

### Externalized Configuration
- YAML-based configuration files
- Environment-specific properties
- Secure credential management

## Security Architecture

- JWT-based authentication
- Role-based authorization (ADMIN/CUSTOMER)
- Secure API endpoints
- Input validation and sanitization