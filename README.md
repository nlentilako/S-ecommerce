# Smart E-Commerce System

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Usage](#usage)
- [GraphQL Schema](#graphql-schema)
- [Validation and Error Handling](#validation-and-error-handling)
- [AOP Implementation](#aop-implementation)
- [Performance Analysis](#performance-analysis)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

This Smart E-Commerce System is a comprehensive web-based Spring Boot application that provides both RESTful and GraphQL APIs for managing e-commerce operations. The system implements modern software engineering practices including layered architecture, dependency injection, validation, exception handling, AOP, and comprehensive API documentation.

## Features

### Core Functionality
- **User Management**: Create, read, update, and delete user accounts
- **Product Catalog**: Comprehensive product management with categories, inventory, and pricing
- **Category Management**: Hierarchical category organization for products
- **RESTful APIs**: Standard REST endpoints following best practices
- **GraphQL APIs**: Flexible queries and mutations for optimized data retrieval
- **Validation**: Comprehensive input validation using Bean Validation
- **Exception Handling**: Centralized error management with meaningful responses
- **API Documentation**: Interactive OpenAPI/Swagger documentation
- **AOP Integration**: Cross-cutting concerns like logging and monitoring
- **Performance Optimization**: Efficient algorithms for sorting, searching, and pagination

### Advanced Features
- **Environment-based Configuration**: Support for dev, test, and prod profiles
- **Constructor-based Dependency Injection**: Following Spring best practices
- **Pagination and Filtering**: Efficient data retrieval with customizable parameters
- **Cross-cutting Concerns**: Centralized logging and performance monitoring

## Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Primary programming language |
| Spring Boot | 3.x | Application framework |
| Spring Web | 3.x | Web application support |
| Spring Data JPA | 3.x | Database access layer |
| Spring GraphQL | 3.x | GraphQL support |
| Springdoc OpenAPI | 2.x | API documentation |
| Hibernate | 6.x | ORM framework |
| Database | Relational | Persistent storage |
| Maven/Gradle | Latest | Build tool |

## Architecture

### Layered Architecture
```
┌─────────────────┐
│   Controller    │ ← REST & GraphQL Endpoints
├─────────────────┤
│     Service     │ ← Business Logic
├─────────────────┤
│   Repository    │ ← Data Access Layer
├─────────────────┤
│    Database     │ ← Data Storage
└─────────────────┘
```

### Design Patterns
- **Dependency Injection**: Constructor-based injection for better testability
- **Layered Architecture**: Separation of concerns between presentation, business, and data layers
- **DTO Pattern**: Data Transfer Objects for API communication
- **Repository Pattern**: Abstraction over data access operations
- **AOP**: Cross-cutting concerns implementation

## Installation

### Prerequisites
- Java 21 or higher
- Maven 3.6+ or Gradle 7+
- Relational database (PostgreSQL, MySQL, etc.)

### Steps
1. Clone the repository:
```bash
git clone <repository-url>
cd smart-ecommerce-system
```

2. Set up the database according to the schema from Module 4

3. Configure environment variables in `application.properties` or `application.yml`

4. Build the project:
```bash
mvn clean install
# or
./gradlew build
```

5. Run the application:
```bash
mvn spring-boot:run
# or
./gradlew bootRun
```

## Configuration

### Environment Profiles
The application supports multiple environments:

- **Development**: `dev` profile
- **Testing**: `test` profile  
- **Production**: `prod` profile

### Configuration Files
- `application.yml`: Base configuration
- `application-dev.yml`: Development-specific settings
- `application-test.yml`: Test environment settings
- `application-prod.yml`: Production environment settings

### Key Configuration Properties
```yaml
# Database Configuration
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce_db
    username: your_username
    password: your_password
    driver-class-name: org.postgresql.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

# Server Configuration
server:
  port: 8080

# Logging Configuration
logging:
  level:
    com.yourpackage: DEBUG
    org.springframework: INFO
```

## API Documentation

The system provides comprehensive API documentation through Springdoc OpenAPI:

- **Swagger UI**: Available at `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: Available at `http://localhost:8080/v3/api-docs`
- **GraphQL Playground**: Available at `http://localhost:8080/graphiql`

### Documentation Features
- Interactive API testing interface
- Request/response examples
- Validation rule documentation
- Error code explanations
- Authentication requirements

## Usage

### REST API Endpoints

#### User Management
- `GET /api/users` - List all users with pagination
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

#### Product Management
- `GET /api/products` - List products with filtering, sorting, pagination
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

#### Category Management
- `GET /api/categories` - List all categories
- `GET /api/categories/{id}` - Get category by ID
- `POST /api/categories` - Create new category
- `PUT /api/categories/{id}` - Update category
- `DELETE /api/categories/{id}` - Delete category

### GraphQL Queries and Mutations

#### Sample Queries
```graphql
# Get all products with pagination
query GetAllProducts($page: Int, $size: Int) {
  products(page: $page, size: $size) {
    id
    name
    price
    category {
      id
      name
    }
  }
}

# Get product by ID
query GetProduct($id: ID!) {
  product(id: $id) {
    id
    name
    description
    price
    stock
    category {
      id
      name
    }
  }
}
```

#### Sample Mutations
```graphql
# Create new product
mutation CreateProduct($input: ProductInput!) {
  createProduct(input: $input) {
    id
    name
    price
    stock
  }
}
```

## GraphQL Schema

### Types
```graphql
type User {
  id: ID!
  firstName: String!
  lastName: String!
  email: String!
  role: String!
  createdAt: String!
  updatedAt: String!
}

type Product {
  id: ID!
  name: String!
  description: String
  price: Float!
  stock: Int!
  category: Category!
  createdAt: String!
  updatedAt: String!
}

type Category {
  id: ID!
  name: String!
  description: String
  parentId: ID
  children: [Category!]!
  products: [Product!]!
  createdAt: String!
  updatedAt: String!
}
```

### Queries
- `users(page: Int, size: Int, sortBy: String, order: SortOrder): UserPage`
- `user(id: ID!): User`
- `products(page: Int, size: Int, categoryId: ID, minPrice: Float, maxPrice: Float): ProductPage`
- `product(id: ID!): Product`
- `categories: [Category!]!`
- `category(id: ID!): Category`

### Mutations
- `createUser(input: UserInput!): User!`
- `updateUser(id: ID!, input: UserInput!): User!`
- `deleteUser(id: ID!): Boolean!`
- `createProduct(input: ProductInput!): Product!`
- `updateProduct(id: ID!, input: ProductInput!): Product!`
- `deleteProduct(id: ID!): Boolean!`
- `createCategory(input: CategoryInput!): Category!`
- `updateCategory(id: ID!, input: CategoryInput!): Category!`
- `deleteCategory(id: ID!): Boolean!`

## Validation and Error Handling

### Validation Rules
- Email format validation
- Password strength requirements
- Product price positive validation
- Stock quantity validation
- Required field validation
- Custom business rule validation

### Error Response Format
```json
{
  "timestamp": "2023-12-01T10:30:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/users",
  "details": [
    {
      "field": "email",
      "message": "Email format is invalid"
    }
  ]
}
```

### Exception Handling
- `@ControllerAdvice` for centralized error handling
- Custom exception classes for business logic errors
- HTTP status code mapping
- Meaningful error messages for clients

## AOP Implementation

### Logging Aspect
- Method execution logging
- Parameter logging
- Return value logging
- Execution time measurement

### Performance Monitoring Aspect
- Method execution time tracking
- Performance metrics collection
- Slow method identification

### Security Aspect
- Access control validation
- Authentication verification
- Authorization checks

### Example AOP Configuration
```java
@Aspect
@Component
public class LoggingAspect {
    
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;
        
        if (duration > 1000) { // Log if execution takes more than 1 second
            log.warn("Method {} took {} ms to execute", 
                     joinPoint.getSignature().getName(), duration);
        }
        
        return result;
    }
}
```

## Performance Analysis

### REST vs GraphQL Performance Comparison

| Aspect | REST | GraphQL |
|--------|------|---------|
| Data Fetching | Multiple endpoints | Single query for multiple entities |
| Over-fetching | Common issue | Minimal over-fetching |
| Network Requests | More requests for complex data | Fewer requests |
| Caching | Standard HTTP caching | Query-specific caching |
| Flexibility | Fixed response structure | Client-defined response |

### Optimization Techniques Implemented
- **Pagination**: Efficient data retrieval with page-based loading
- **Sorting Algorithms**: Optimized sorting using efficient algorithms
- **Search Algorithms**: Binary search and other efficient search methods
- **Database Indexing**: Strategic indexing for query optimization
- **Connection Pooling**: Efficient database connection management

### Performance Metrics
- Response time monitoring
- Throughput measurement
- Memory usage tracking
- Database query optimization

## Testing

### Test Strategy
- **Unit Tests**: Individual method and class testing
- **Integration Tests**: Component interaction testing
- **API Tests**: Endpoint functionality validation
- **Performance Tests**: Load and stress testing

### Testing Tools
- JUnit 5 for unit testing
- Mockito for mocking dependencies
- REST Assured for API testing
- GraphQL Test for GraphQL endpoint testing

### Test Coverage
- Minimum 80% code coverage requirement
- Critical business logic 100% coverage
- API endpoints comprehensive testing

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── ecommerce/
│   │           ├── SmartEcommerceApplication.java
│   │           ├── config/           # Configuration classes
│   │           ├── controller/       # REST and GraphQL controllers
│   │           ├── service/          # Business logic layer
│   │           ├── repository/       # Data access layer
│   │           ├── model/            # Entity and DTO classes
│   │           ├── aspect/           # AOP aspects
│   │           ├── exception/        # Custom exceptions
│   │           └── validator/        # Custom validators
│   └── resources/
│       ├── application.yml          # Main configuration
│       ├── application-dev.yml      # Development config
│       ├── application-prod.yml     # Production config
│       └── static/                  # Static resources
└── test/
    └── java/
        └── com/
            └── ecommerce/
                └── tests/           # Test classes
```

## Contributing

### Development Guidelines
1. Follow the layered architecture pattern
2. Use constructor-based dependency injection
3. Implement proper validation and error handling
4. Write comprehensive unit and integration tests
5. Maintain API documentation with Springdoc OpenAPI
6. Apply AOP for cross-cutting concerns
7. Follow Java and Spring Boot best practices

### Code Standards
- Use meaningful variable and method names
- Write clear and concise comments
- Follow the Single Responsibility Principle
- Implement proper exception handling
- Use appropriate design patterns

### Branching Strategy
- `main`: Production-ready code
- `develop`: Integration branch for features
- `feature/*`: Feature development branches
- `hotfix/*`: Urgent production fixes

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Spring Boot Framework
- Spring GraphQL
- Springdoc OpenAPI
- Hibernate ORM
- GraphQL Java
- Various open-source libraries and tools