A Spring Boot RESTful backend application designed to manage conference room bookings within an organization.
The system allows employees to book rooms, check availability, manage approvals, and import employee data via an external CSV-to-JSON API.

This project demonstrates clean architecture, proper entity relationships, DTO usage, transaction management, and external API integration.

🚀 Features

✅ Import employees via external CSV API

✅ Create and manage rooms & equipment

✅ Book conference rooms

✅ Approve or reject bookings

✅ View room availability

✅ View employee booking history

✅ Cancel bookings

✅ Maximum 9 REST endpoints (as per design constraint)

🏗️ Tech Stack

Java 17+

Spring Boot

Spring Data JPA

Hibernate

REST APIs

MySQL / H2

Maven

DTO Pattern

Global Exception Handling

🏛️ Architecture

The project follows a clean layered architecture:

Controller → Service → Repository → Database

✔ Controller Layer

Handles HTTP requests and responses.

✔ Service Layer

Contains business logic and transaction management.

✔ Repository Layer

Handles database interaction using Spring Data JPA.

✔ DTO Layer

Prevents direct exposure of entities.

🗂️ Entities
Employee

id

name

email

employeeCode

Department

id

name

Room

id

name

capacity

location

Equipment

id

name

type

Booking

id

startTime

endTime

purpose

status

Approval

id

approvedBy

approvalTime

remarks

🔗 Entity Relationships

One Department → Many Employees

One Employee → Many Bookings

One Room → Many Bookings

One Booking → One Approval

Many-to-Many → Room & Equipment

📦 REST Endpoints (Limited to 9)
Method	Endpoint	Description
POST	/employees/import	Import employees from CSV API
POST	/rooms	Create room
POST	/equipment	Create equipment
POST	/bookings	Book room
PUT	/bookings/{id}/approve	Approve booking
PUT	/bookings/{id}/reject	Reject booking
GET	/rooms/availability	Check room availability
GET	/employees/{id}/bookings	View employee bookings
DELETE	/bookings/{id}	Cancel booking
🔄 External API Integration

Integrated with a CSV-to-JSON API

Handles:

API failures

Invalid responses

Data validation before persistence

🔐 Validation & Exception Handling

@Valid for request validation

Custom exception classes

Global exception handler using @ControllerAdvice

Proper HTTP status codes

🔁 Transaction Management

@Transactional used in service layer

Prevents partial updates

Ensures atomic booking and approval operations

📊 Database Design

Proper normalization

Logical entity mapping

Foreign key constraints

Prevention of double booking using time conflict validation

🧠 What This Project Demonstrates

Clean backend architecture

REST API design principles

Database relationship modeling

DTO implementation

External API consumption

Enterprise-level backend structure

🛠️ How to Run
# Clone repository
git clone <your-repo-url>

# Navigate to project
cd conference-room-booking

# Run application
mvn spring-boot:run

📌 Author

Kunj Sharma
Backend Developer | Spring Boot | REST APIs | JPA | Clean Architecture
