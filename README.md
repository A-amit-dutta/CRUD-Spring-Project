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
│   │           ├── index.html
│   │           ├── styles.css
│   │           └── script.js
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

### Frontend (HTML/CSS/JavaScript)
- 🎨 Modern, responsive UI
- ✅ Create/Edit/Delete users
- 🔍 Search functionality
- 📱 Mobile-friendly design
- 🎯 Real-time table updates
- 💬 User-friendly notifications
- ⚡ Smooth animations

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
- Modern web browser (Chrome, Firefox, Safari, Edge)

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

### 3. Access Frontend

Once the backend is running, open your browser and navigate to:
```
http://localhost:8080
```

The frontend will be automatically served by Spring Boot from the `static` folder.

## Usage

### Create a User
1. Fill in the form fields (ID, Name, Email, Age)
2. Click "Create User" button
3. User appears in the table below

### View All Users
- Click "Show All" button to display all users
- Users are automatically loaded when page refreshes

### Search Users
1. Enter name and/or email in search fields
2. Click "Search" button
3. Table shows matching results

### Edit User
1. Click "Edit" button on any user row
2. Form fields auto-populate with user data
3. Modify values as needed
4. Click "Update User" button

### Delete User
1. Click "Delete" button on any user row
2. Confirm deletion in popup
3. User removed from database

## Technical Details

### Backend Stack
- **Framework**: Spring Boot 4.0.6
- **Language**: Java 25
- **Build Tool**: Maven
- **API Type**: REST

### Frontend Stack
- **HTML5**: Structure & layout
- **CSS3**: Modern styling with gradients and animations
- **Vanilla JavaScript**: No dependencies, pure ES6+
- **Fetch API**: For HTTP communication

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

## Browser Support

- ✅ Chrome/Chromium (latest)
- ✅ Firefox (latest)
- ✅ Safari (latest)
- ✅ Edge (latest)
- ✅ Mobile browsers (iOS Safari, Chrome Android)

## Responsive Design

The frontend is fully responsive and works great on:
- 📱 Mobile phones (320px+)
- 📱 Tablets (768px+)
- 🖥️ Desktops (1024px+)

## Known Issues (Learning Project)

This is a learning project. The following issues are intentional for educational purposes and will be fixed in future versions:

1. **HTTP Status Codes** - `UserNotFoundException` returns 400 (Bad Request) instead of 404 (Not Found)
2. **Inconsistent Error Handling** - `getAllUser()` throws exception when empty, but `searchUser()` returns empty list
3. **Default Search Parameters** - Search endpoint has hardcoded default values (`defaultValue = "Lil"`, `defaultValue = "email"`)
4. **Code Cleanup** - Unused empty constructor in `UserService` class

**Frontend Issues (FIXED):**
- ✅ DELETE response parsing (changed from JSON to text)
- ✅ Input validation (email format, age range 0-150)

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

## Troubleshooting

### Frontend not loading
- Ensure backend is running on port 8080
- Clear browser cache (Ctrl+Shift+Delete)
- Check browser console for errors (F12)

### API errors
- Check backend logs for error details
- Verify CORS configuration
- Ensure JSON payload format is correct

### Port already in use
- Change port in `application.properties` (if exists)
- Or kill process using port 8080

## License

This project is part of Java Learning series.

## Author

Created as a learning project for Spring Boot and Frontend integration.
