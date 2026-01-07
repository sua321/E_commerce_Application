# E-Commerce Application Backend

## 📌 Overview
This project is a backend REST API for an E-commerce platform built using **Java 21** and **Spring Boot**. It handles users management, shopping cart operations, and integrates with external services to fetch product details.

This application demonstrates modern backend development practices, including database migrations, external API consumption, and a layered architecture.

## 🚀 Tech Stack
*   **Language:** Java 21
*   **Framework:** Spring Boot (Web, Data JPA)
*   **Database:** MySQL
*   **Migration Tool:** Flyway
*   **HTTP Client:** Spring RestClient (Synchronous)
*   **Utilities:** Lombok, Maven

## ✨ Key Features
*   **External Product Integration:** Fetches product data (Short and Full details) from an external 3rd-party API using `RestClient`.
*   **User Management:** Supports distinct roles including Admin, Vendor, and Customer.
*   **Shopping Cart:** Persistent cart management for users stored in MySQL.
*   **Database Versioning:** Automated schema management and migrations using Flyway.
*   **Entity Relationships:** Complex JPA mappings for Users, Addresses, Phone Numbers, and Favorites.

## ⚙️ Configuration
The application requires specific environment variables or configuration in `application.yaml` to run securely.

### Environment Variables
| Variable | Description |
| :--- | :--- |
| `DB_PASSWORD` | Your MySQL root password |

### Application Properties
The application connects to an external API defined in `src/main/resources/application.yaml`:
```yaml
external:
  api:
    url: "https://api.example.com" # Replace with actual API URL
```

## 🛠️ How to Run

### Prerequisites
*   Java 21 SDK
*   Maven
*   MySQL Server running on port `3306`

### Steps
1.  **Clone the repository**
    ```bash
    git clone https://github.com/sua321/E_commerce_Application.git
    cd E_commerce_Application
    ```

2.  **Configure Database**
    Ensure MySQL is running. The application will automatically create the database `ecommerce_application` if it doesn't exist.

3.  **Run with Maven**
    Pass your database password as an environment variable or argument:
    ```bash
    # Linux/Mac
    export DB_PASSWORD=your_password
    ./mvnw spring-boot:run

    # Windows (CMD)
    set DB_PASSWORD=your_password
    mvnw spring-boot:run
    ```

## 📂 Project Structure
```
src/main/java/com/me/e_commerce_application
├── controllers      # REST Controllers (API Endpoints)
├── services         # Business Logic & External API Calls
├── daos             # Data Access Objects (External Data)
├── dto              # Data Transfer Objects (API Responses)
├── models           # JPA Entities (Database Tables)
├── repositories     # Spring Data JPA Repositories
└── configurations   # App Config (RestClient, etc.)
```
