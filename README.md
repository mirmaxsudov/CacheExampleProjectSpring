CacheSpringProject
CacheSpringProject is a Spring Boot application designed to manage groups and students with caching implemented for enhanced performance.

Table of Contents
Features
Technologies Used
Getting Started
Prerequisites
Installation
Usage
Endpoints
Contributing
License
Features
CRUD Operations: Allows Create, Read, Update, and Delete operations for groups and students.
Caching: Implements caching using Spring's caching annotations for optimized performance.
Global Exception Handling: Provides centralized error handling for validation errors and custom exceptions.
Technologies Used
Spring Boot: For building and running the application.
Spring Data JPA: For data access and persistence.
Spring Cache: For caching mechanism.
Lombok: For reducing boilerplate code.
Java Persistence API (JPA): For managing relational data in Java applications.
Jakarta Persistence (formerly Java EE persistence): For defining persistence APIs.
JUnit: For unit testing.
Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher
Maven
Git
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/your_username/CacheSpringProject.git
Navigate to the project directory:
bash
Copy code
cd CacheSpringProject
Build the project:
bash
Copy code
mvn clean install
Run the application:
bash
Copy code
java -jar target/CacheSpringProject-<version>.jar
Usage
Once the application is up and running, you can interact with it using HTTP requests to the defined endpoints.

Endpoints
GET /api/group/: Retrieves all groups.
GET /api/group/{groupId}: Retrieves a group by its ID.
GET /api/group/get-group-with-students: Retrieves groups along with their associated students.
POST /api/group/: Creates a new group.
GET /api/student/: Retrieves all students.
GET /api/student/{studentId}: Retrieves a student by its ID.
POST /api/student/: Creates a new student.
Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

License
This project is licensed under the MIT License.

