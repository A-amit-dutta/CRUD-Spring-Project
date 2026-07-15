// API Base URL
const API_URL = 'http://localhost:8080/users';

// DOM Elements
const userForm = document.getElementById('userForm');
const userIdInput = document.getElementById('userId');
const userNameInput = document.getElementById('userName');
const userEmailInput = document.getElementById('userEmail');
const userAgeInput = document.getElementById('userAge');
const createBtn = document.getElementById('createBtn');
const updateBtn = document.getElementById('updateBtn');
const searchBtn = document.getElementById('searchBtn');
const showAllBtn = document.getElementById('showAllBtn');
const usersTableBody = document.getElementById('usersTableBody');
const messageDiv = document.getElementById('message');
const loadingDiv = document.getElementById('loading');

// Event Listeners
createBtn.addEventListener('click', createUser);
updateBtn.addEventListener('click', updateUser);
searchBtn.addEventListener('click', searchUsers);
showAllBtn.addEventListener('click', loadAllUsers);

// Initialize - Load all users on page load
document.addEventListener('DOMContentLoaded', () => {
    loadAllUsers();
});

// Load all users
async function loadAllUsers() {
    showLoading(true);
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const users = await response.json();
        displayUsers(users);
        showMessage('Users loaded successfully', 'success');
    } catch (error) {
        console.error('Error loading users:', error);
        showMessage('Error loading users: ' + error.message, 'error');
        usersTableBody.innerHTML = '<tr class="empty-row"><td colspan="5">Error loading users</td></tr>';
    } finally {
        showLoading(false);
    }
}

// Create User
async function createUser() {
    const id = userIdInput.value;
    const name = userNameInput.value;
    const email = userEmailInput.value;
    const age = userAgeInput.value;

    if (!id || !name || !email || !age) {
        showMessage('Please fill in all fields', 'error');
        return;
    }

    if (!isValidEmail(email)) {
        showMessage('Please enter a valid email address', 'error');
        return;
    }

    const ageNum = parseInt(age);
    if (isNaN(ageNum) || ageNum < 0 || ageNum > 150) {
        showMessage('Age must be between 0 and 150', 'error');
        return;
    }

    const userData = {
        id: parseInt(id),
        name: name,
        email: email,
        age: ageNum
    };

    showLoading(true);
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const createdUser = await response.json();
        showMessage('User created successfully!', 'success');
        userForm.reset();
        loadAllUsers();
    } catch (error) {
        console.error('Error creating user:', error);
        showMessage('Error creating user: ' + error.message, 'error');
    } finally {
        showLoading(false);
    }
}

// Update User
async function updateUser() {
    const id = userIdInput.value;
    const name = userNameInput.value;
    const email = userEmailInput.value;
    const age = userAgeInput.value;

    if (!id || !name || !email || !age) {
        showMessage('Please fill in all fields', 'error');
        return;
    }

    if (!isValidEmail(email)) {
        showMessage('Please enter a valid email address', 'error');
        return;
    }

    const ageNum = parseInt(age);
    if (isNaN(ageNum) || ageNum < 0 || ageNum > 150) {
        showMessage('Age must be between 0 and 150', 'error');
        return;
    }

    const userData = {
        id: parseInt(id),
        name: name,
        email: email,
        age: ageNum
    };

    showLoading(true);
    try {
        const response = await fetch(API_URL, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const updatedUser = await response.json();
        showMessage('User updated successfully!', 'success');
        userForm.reset();
        loadAllUsers();
    } catch (error) {
        console.error('Error updating user:', error);
        showMessage('Error updating user: ' + error.message, 'error');
    } finally {
        showLoading(false);
    }
}

// Delete User
async function deleteUser(userId) {
    if (!confirm(`Are you sure you want to delete user ${userId}?`)) {
        return;
    }

    showLoading(true);
    try {
        const response = await fetch(`${API_URL}/${userId}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.text();
        showMessage('User deleted successfully!', 'success');
        loadAllUsers();
    } catch (error) {
        console.error('Error deleting user:', error);
        showMessage('Error deleting user: ' + error.message, 'error');
    } finally {
        showLoading(false);
    }
}

// Edit User - Populate form with user data
async function editUser(userId) {
    showLoading(true);
    try {
        const response = await fetch(`${API_URL}/${userId}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const user = await response.json();
        
        userIdInput.value = user.id;
        userNameInput.value = user.name;
        userEmailInput.value = user.email;
        userAgeInput.value = user.age;
        
        // Scroll to form
        document.querySelector('.form-container').scrollIntoView({ behavior: 'smooth' });
        showMessage('User loaded for editing', 'info');
    } catch (error) {
        console.error('Error fetching user:', error);
        showMessage('Error fetching user: ' + error.message, 'error');
    } finally {
        showLoading(false);
    }
}

// Search Users
async function searchUsers() {
    const searchName = document.getElementById('searchName').value;
    const searchEmail = document.getElementById('searchEmail').value;

    if (!searchName && !searchEmail) {
        showMessage('Please enter a name or email to search', 'error');
        return;
    }

    showLoading(true);
    try {
        const params = new URLSearchParams();
        if (searchName) params.append('name', searchName);
        if (searchEmail) params.append('email', searchEmail);

        const response = await fetch(`${API_URL}/search?${params}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const users = await response.json();
        
        if (users.length === 0) {
            showMessage('No users found matching your criteria', 'info');
        } else {
            showMessage(`Found ${users.length} user(s)`, 'success');
        }
        
        displayUsers(users);
    } catch (error) {
        console.error('Error searching users:', error);
        showMessage('Error searching users: ' + error.message, 'error');
    } finally {
        showLoading(false);
    }
}

// Display Users in Table
function displayUsers(users) {
    if (users.length === 0) {
        usersTableBody.innerHTML = '<tr class="empty-row"><td colspan="5">No users found</td></tr>';
        return;
    }

    usersTableBody.innerHTML = users.map(user => `
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.age}</td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-edit" onclick="editUser(${user.id})">Edit</button>
                    <button class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button>
                </div>
            </td>
        </tr>
    `).join('');
}

// Show/Hide Loading
function showLoading(show) {
    loadingDiv.style.display = show ? 'block' : 'none';
}

// Show Message
function showMessage(message, type) {
    messageDiv.textContent = message;
    messageDiv.className = `message ${type}`;
    messageDiv.style.display = 'block';

    // Auto-hide message after 5 seconds
    setTimeout(() => {
        messageDiv.style.display = 'none';
    }, 5000);
}

// Validate email format
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}
