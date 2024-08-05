# E-commerce Backend System

## Overview

This project is an E-commerce backend system built using Spring Boot. It provides functionalities for managing products, user authentication, and authorization using JWT, and a rating system for products.

## Technologies Used

- Java
- Spring Boot
- Maven
- MySQL
- JWT

## Key Components

### Controllers

1. **AdminProductController**:
    - `POST /createProduct`: Create a new product.
    - `DELETE /{productId}/delete`: Delete a product by ID.
    - `GET /all`: Retrieve all products.
    - `PUT /{productId}/update`: Update a product by ID.
    - `POST /creates`: Create multiple products.

2. **RatingController**:
    - `POST /create`: Create a new rating for a product.
    - `GET /product/{productId}`: Get ratings for a specific product.

3. **ProductController**:
    - `GET /products`: Find products by category with various filters.
    - `GET /products/id/{productId}`: Find a product by ID.

4. **CartController**:
    - `GET /findCart`: Find the user's cart.
    - `POST /add`: Add an item to the cart.

### Filters

- **JwtFilter**:
    - Extracts the JWT token from the `Authorization` header.
    - Validates the token and sets the authentication in the security context if valid.

### Service Layer

Handles business logic for products, ratings, and users.

### Repository Layer

Manages database interactions.

### Configuration Files

Contains Spring Boot and security settings.

## Features

- JWT-based authentication and authorization.
- CRUD operations for products.
- Rating system for products.
- Filtering and sorting products by various criteria.

## Setup Instructions

1. **Clone the repository:**
    ```sh
    git clone https://github.com/rahulpannati513/Ecommerce-Backend.git
    cd Ecommerce-Backend
    ```

2. **Configure the database:**
    - Update the `application.properties` file with your MySQL database credentials.

3. **Build the project:**
    ```sh
    mvn clean install
    ```

4. **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication

- **Signup:**
    - `POST /api/auth/signup`
    - Request Body: `{ "username": "string", "password": "string", "email": "string", "fullName": "string", "phoneNumber": "string" }`

- **Login:**
    - `POST /api/auth/login`
    - Request Body: `{ "username": "string", "password": "string" }`

### Product Management

- **Create Product:**
    - `POST /api/products`
    - Request Body: `{ "name": "string", "description": "string", "price": "decimal", "quantity": "int" }`

- **Retrieve All Products:**
    - `GET /api/products`

- **Get Product by ID:**
    - `GET /api/products/{id}`

- **Update Product:**
    - `PUT /api/products/{id}`
    - Request Body: `{ "name": "string", "description": "string", "price": "decimal", "quantity": "int" }`

- **Delete Product:**
    - `DELETE /api/products/{id}`

### Cart Management

- **Find User Cart:**
    - `GET /api/cart/findCart`

- **Add Item to Cart:**
    - `POST /api/cart/add`
    - Request Body: `{ "productId": "long", "quantity": "int" }`
