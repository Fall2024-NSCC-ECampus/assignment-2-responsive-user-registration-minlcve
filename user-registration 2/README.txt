# User Registration Application

This is a simple user registration and login system built using Spring Boot and MySQL. The application allows users to register with a unique username and email, store their hashed password securely in a MySQL database, and handle login functionality.

## Features

- **User Registration**: Collects user input for username, email, and password.
- **Password Hashing**: Passwords are securely hashed before being stored in the database.
- **Role Management**: Users are given a default role of 'user'. You can extend this feature to include more roles.
- **Timestamps**: Tracks when users are created and updated.

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 23 or higher**
- **Spring Boot** (configured with Maven or Gradle)
- **MySQL** (with a running MySQL server)
- **Maven** or **Gradle** for building the project

## Database Setup

1. Create a MySQL database called `user_registration`.
2. Run the following SQL command to create the `users` table:

```sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,  -- Username must be unique
  email VARCHAR(255) NOT NULL UNIQUE,     -- Email must be unique
  password VARCHAR(255) NOT NULL,         -- Password will store the hashed password
  role VARCHAR(50) DEFAULT 'user',       -- Default role set to 'user'
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Tracks when the user was created
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Tracks when user info is updated
);
