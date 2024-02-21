// src/utils/api.js
import axios from 'axios';

// Create an Axios instance with default configuration
const instance = axios.create({
    baseURL: 'http://localhost:8080', // Your API base URL
    headers: {
        'Content-Type': 'application/json',
    },
});

// Function to get the auth token from local storage
function getAuthToken() {
    return localStorage.getItem("auth_token");
}

// Request interceptor to add the auth token to request headers
instance.interceptors.request.use(
    (config) => {
        const token = getAuthToken();
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Optional: Response interceptor to handle global errors, like token expiration
instance.interceptors.response.use(
    (response) => {
        // Your response success handling
        return response;
    },
    (error) => {
        // Here you could check for specific error responses, such as a 401 Unauthorized
        if (error.response && error.response.status === 401) {
            // Handle token expiration or invalid token case here
            // For example, you could remove the token and redirect to login
            localStorage.removeItem("auth_token");
            // Optionally, redirect the user to the login page, or display a message
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

export default instance;
