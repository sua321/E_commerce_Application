# E-Commerce Application Backend

## 📌 Overview
This project is a robust backend REST API for an E-commerce platform built using **Java 21** and **Spring Boot**. 

A standout feature of this application is its **External Vendor Integration**. Instead of managing a static local inventory, the application acts as an aggregator, dynamically fetching product details (items) from external vendor APIs. This allows for real-time inventory updates and a decentralized product management system.

The application also handles secure user authentication, registration with custom validation, and persistent data management.

## 🚀 Tech Stack
*   **Language:** Java 21
*   **Framework:** Spring Boot 3.x (Web, Data JPA, Security)
*   **Database:** MySQL
*   **Migration Tool:** Flyway
*   **Security:** Spring Security (Stateless JWT)
*   **HTTP Client:** Spring RestClient (for external API calls)
*   **Utilities:** Lombok, Maven, Dotenv

## ✨ Key Features
*   **External Product Aggregation:** Utilizes Spring's `RestClient` to consume third-party vendor APIs, fetching product data (Short and Full details) on demand.
*   **Secure Authentication:** Implements stateless authentication using **JSON Web Tokens (JWT)**.
*   **User Management:** 
    *   Registration and Login for Customers and Vendors.
    *   Custom Validators (e.g., Age Verification, Email Format).
    *   Password encryption using `BCrypt`.
*   **Database Versioning:** Automated schema management and migrations using Flyway.
*   **Secure Configuration:** Sensitive credentials (passwords, secrets) are managed via a local `.env` file, keeping them out of the source code.

## ⚙️ Configuration

The application uses the **`spring-dotenv`** dependency to securely manage secrets. This library automatically reads the `.env` file and injects the values (like the database password) into the Spring environment. You must create a `.env` file in the root directory of the project (`D:/MyProject/E_commerce_Application/.env`) before running the app.

### Required Environment Variables
Create a file named `.env` and add the following:

```properties
# Database Configuration
DB_PASSWORD=your_mysql_root_password

# Security
JWT_SECRET=your_secure_random_secret_string_at_least_32_chars_long

# External Integrations
EXTERNAL_API=https://api.vendor-example.com/items
```

> **Note:** Do not commit the `.env` file to version control.

## 🛠️ How to Run

### Prerequisites
*   Java 21 SDK
*   Maven
*   MySQL Server running on port `3306`

### Steps
1.  **Clone the repository**
    ```bash
    git clone https://github.com/your-username/E_commerce_Application.git
    cd E_commerce_Application
    ```

2.  **Setup Database**
    Ensure MySQL is running. The application is configured to create the database `ecommerce_application` automatically if it doesn't exist (via the connection URL).

3.  **Build and Run**
    ```bash
    mvn spring-boot:run
    ```
    *Alternatively, you can run the `ECommerceApplication` class from your IDE.*

## 🤝 Development Workflow
*   **Branching:** The project uses `master` as the default branch.
*   **Tooling:** Code changes, commits, and pushes are managed using **IntelliJ IDEA's** integrated Git tools.

## 🔌 API Endpoints

### User Management
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/user/register` | Register a new Customer or Vendor. Requires JSON body. |
| `POST` | `/user/login` | Authenticate via Username or Email. Returns a JWT. |
| `POST` | `/user/validate` | Validates a JWT token sent in the `Authorization` header. |

### Items (External)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/item/all` | Fetches a list of items from the configured external vendor API. |
| `GET` | `/item/{id}` | Fetches details for a specific item. |

## 📂 Project Structure
```
src/main/java/com/me/e_commerce_application
├── configurations   # Security, App, and RestClient configs
├── controllers      # REST API endpoints
├── daos             # Data Access Objects (Input validation)
├── dto              # Data Transfer Objects (Responses)
├── models           # JPA Entities
├── repositories     # Database interfaces
├── services         # Business logic & External API integration
└── validators       # Custom annotations (e.g., @AgeVerification)
```