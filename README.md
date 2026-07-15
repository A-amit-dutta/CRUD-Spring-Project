# User Management System - Full Stack

A simple CRUD application for managing users built with Spring Boot backend and vanilla HTML/CSS/JavaScript frontend.

## Project Structure

```
MyProjectFai/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/CRUDbyFM/
│   │   │       ├── MyProjectFai.java (Main Application)
│   │   │       └── App/
│   │   │           ├── Controller/
│   │   │           │   ├── UserController.java
│   │   │           │   └── UserNotFoundException.java
│   │   │           ├── Model/
│   │   │           │   └── User.java
│   │   │           ├── Service/
│   │   │           │   └── UserService.java
│   │   │           ├── Exceptions/
│   │   │           │   └── GlobalExceptionHandler.java
│   │   │           └── Config/
│   │   │               └── CorsConfig.java (NEW - Enables frontend communication)
│   │   └── resources/
│   │       └── static/ (NEW - Frontend files)
│   └── test/
├── pom.xml
└── README.md
```

## Features

### Backend (Spring Boot REST API)
- ✅ Create User (POST)
- ✅ Read Users (GET all/single)
- ✅ Update User (PUT)
- ✅ Delete User (DELETE)
- ✅ Search Users (by name/email)
- ✅ Global Exception Handling
- ✅ CORS Support

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get user by ID |
| GET | `/users/search` | Search users (by name/email) |
| POST | `/users` | Create new user |
| PUT | `/users` | Update user |
| DELETE | `/users/{id}` | Delete user |
| GET | `/users/info` | Get user agent info |

## Setup Instructions

### 1. Prerequisites
- Java 17+ installed
- Maven installed

### 2. Build & Run Backend

```bash
# Navigate to project directory
cd MyProjectFai

# Clean and build
mvn clean install

# Run the application
mvn spring-boot:run
```

The backend will start at: `http://localhost:8080`


### Create a User


### View All Users


### Search Users

### Edit User


### Delete User


## Technical Details

### Backend Stack
- **Framework**: Spring Boot 4.0.6
- **Language**: Java 25
- **Build Tool**: Maven
- **API Type**: REST


### Data Model

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "age": 30
}
```

## CORS Configuration

CORS is enabled to allow frontend (running on same server) to communicate with backend API.

**Allowed Origins**: 
- `http://localhost:8080`
- `http://127.0.0.1:8080`

**Allowed Methods**: GET, POST, PUT, DELETE, OPTIONS

## Error Handling

- ✅ Form validation on frontend
- ✅ User-friendly error messages
- ✅ HTTP error handling
- ✅ Empty state messages
- ✅ Global exception handler on backend


## Known Issues (Learning Project)

This is a learning project. The following issues are intentional for educational purposes and will be fixed in future versions:

1. **HTTP Status Codes** - `UserNotFoundException` returns 400 (Bad Request) instead of 404 (Not Found)
2. **Inconsistent Error Handling** - `getAllUser()` throws exception when empty, but `searchUser()` returns empty list
3. **Default Search Parameters** - Search endpoint has hardcoded default values (`defaultValue = "Lil"`, `defaultValue = "email"`)
4. **Code Cleanup** - Unused empty constructor in `UserService` class


## Future Enhancements

- [ ] Fix HTTP status codes (404 for not found)
- [ ] Standardize empty result handling across all endpoints
- [ ] Remove hardcoded default search parameters
- [ ] Add user authentication/authorization
- [ ] Add pagination for large user lists
- [ ] Add sorting by columns
- [ ] Add bulk operations
- [ ] Add export to CSV/PDF
- [ ] Add role-based access control
- [ ] Add activity logging

### API errors
- Check backend logs for error details

### Port already in use
- Change port in `application.properties` (if exists)
- Or kill process using port 8080

## License

This project is part of Java Learning series.

## Author

Created as a learning project for Spring Boot.
