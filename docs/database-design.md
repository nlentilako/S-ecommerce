# Database Design

## Overview

The Smart E-Commerce System uses a relational database schema designed to support all e-commerce operations including user management, product catalog, inventory, orders, and payments. The database follows normalized design principles while optimizing for common e-commerce queries.

## Entity Relationship Diagram

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│     Users       │    │    Products     │    │   Categories    │
│─────────────────│    │─────────────────│    │─────────────────│
│ id (PK)         │    │ id (PK)         │    │ id (PK)         │
│ email (U)       │───→│ category_id (FK)│    │ name            │
│ first_name      │    │ name            │    │ description     │
│ last_name       │    │ description     │    │ parent_id (FK)  │
│ password_hash   │    │ price           │    │ created_at      │
│ role            │    │ stock_quantity  │    │ updated_at      │
│ created_at      │    │ status          │    └─────────────────┘
│ updated_at      │    │ created_at      │
└─────────────────┘    │ updated_at      │
                       └─────────────────┘
                              │
                              │
                              ▼
┌─────────────────┐    ┌─────────────────┐
│    Orders       │    │ Order_Items     │
│─────────────────│    │─────────────────│
│ id (PK)         │    │ id (PK)         │
│ user_id (FK)    │───→│ order_id (FK)   │
│ total_amount    │    │ product_id (FK) │
│ status          │    │ quantity        │
│ created_at      │    │ price           │
│ updated_at      │    └─────────────────┘
└─────────────────┘
```

## Table Descriptions

### 1. Users Table
Stores user account information for customers and administrators.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for user |
| email | VARCHAR(255) | NOT NULL, UNIQUE | User's email address |
| first_name | VARCHAR(100) | NOT NULL | User's first name |
| last_name | VARCHAR(100) | NOT NULL | User's last name |
| password_hash | VARCHAR(255) | NOT NULL | Hashed password |
| role | VARCHAR(50) | NOT NULL | User role (ADMIN, CUSTOMER) |
| created_at | TIMESTAMP | NOT NULL | Account creation timestamp |
| updated_at | TIMESTAMP | NOT NULL | Last update timestamp |

### 2. Categories Table
Hierarchical product categorization system.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique category identifier |
| name | VARCHAR(100) | NOT NULL | Category name |
| description | TEXT | NULL | Category description |
| parent_id | BIGINT | FOREIGN KEY | Self-referencing for hierarchy |
| created_at | TIMESTAMP | NOT NULL | Creation timestamp |
| updated_at | TIMESTAMP | NOT NULL | Last update timestamp |

### 3. Products Table
Product catalog with pricing and inventory information.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique product identifier |
| name | VARCHAR(255) | NOT NULL | Product name |
| description | TEXT | NULL | Product description |
| price | DECIMAL(10,2) | NOT NULL | Product price |
| stock_quantity | INT | NOT NULL | Available stock |
| category_id | BIGINT | FOREIGN KEY | Category reference |
| status | VARCHAR(20) | NOT NULL | Product status (ACTIVE, INACTIVE) |
| created_at | TIMESTAMP | NOT NULL | Creation timestamp |
| updated_at | TIMESTAMP | NOT NULL | Last update timestamp |

### 4. Orders Table
Customer order information and status tracking.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique order identifier |
| user_id | BIGINT | FOREIGN KEY | Customer reference |
| total_amount | DECIMAL(10,2) | NOT NULL | Order total amount |
| status | VARCHAR(50) | NOT NULL | Order status (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED) |
| created_at | TIMESTAMP | NOT NULL | Order creation timestamp |
| updated_at | TIMESTAMP | NOT NULL | Last status update timestamp |

### 5. Order_Items Table
Individual items within an order.

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique order item identifier |
| order_id | BIGINT | FOREIGN KEY | Order reference |
| product_id | BIGINT | FOREIGN KEY | Product reference |
| quantity | INT | NOT NULL | Quantity ordered |
| price | DECIMAL(10,2) | NOT NULL | Price at time of order |
| created_at | TIMESTAMP | NOT NULL | Creation timestamp |

## Indexes

### Primary Keys
- Each table has a primary key on the `id` column with auto-increment

### Foreign Key Constraints
- `products.category_id` → `categories.id`
- `products.user_id` → `users.id` (for product creators)
- `orders.user_id` → `users.id`
- `order_items.order_id` → `orders.id`
- `order_items.product_id` → `products.id`
- `categories.parent_id` → `categories.id` (self-referencing)

### Additional Indexes
- `users.email`: Unique index for efficient login
- `products.category_id`: Index for category filtering
- `products.price`: Index for price-based queries
- `orders.user_id`: Index for user order history
- `orders.status`: Index for order status filtering
- `categories.parent_id`: Index for hierarchical queries

## Database Constraints

### Referential Integrity
- Cascade delete for order items when order is deleted
- Restrict deletion of categories with products
- Maintain data consistency across related tables

### Check Constraints
- Product price must be positive
- Stock quantity must be non-negative
- Order status must be valid value
- User role must be valid value

## Performance Considerations

### Query Optimization
- Proper indexing on frequently queried columns
- Efficient JOIN operations between related tables
- Pagination support for large result sets

### Normalization
- Third normal form (3NF) compliance
- Elimination of data redundancy
- Maintained referential integrity

### Scalability
- Partitioning strategy for large tables (orders)
- Read replica support for reporting
- Caching layer integration points