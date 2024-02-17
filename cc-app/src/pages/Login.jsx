// src/pages/Login.jsx
import React from 'react';
import UserLogin from '../components/auth/UserLogin'; // Adjust the import path as necessary

const Login = () => {
    return (
        <div className="login-page">
            <h1>Login</h1>
            <UserLogin />
        </div>
    );
};

export default Login;