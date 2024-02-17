// UserLogin.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './LoginSignup.css'; // Corrected path (assuming it is in the same directory)

const UserLogin = () => {
    const [credentials, setCredentials] = useState({ email: '', password: '' });
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setCredentials({ ...credentials, [name]: value });
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('/api/auth/login', credentials);
            localStorage.setItem('authToken', response.data.token); // Assuming JWT token is returned
            navigate('/home');
        } catch (error) {
            console.error("Login error:", error.response.data);
            // Handle login error here
        }
    };

    const handleGoogleLogin = () => {
        window.location.href = '/oauth2/authorization/google'; // Your Spring Boot redirect URI for Google OAuth
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">Login</div>
                <div className="underline"></div>
            </div>
            <form onSubmit={handleFormSubmit}>
                <div className="inputs">
                    <div className="input">
                        <input type="email" name="email" placeholder="Email Address" onChange={handleInputChange} />
                    </div>
                    <div className="input">
                        <input type="password" name="password" placeholder="Password" onChange={handleInputChange} />
                    </div>
                </div>
                <div className="submit-container">
                    <button type="submit" className="submit">Login</button>
                </div>
            </form>
            <div className="submit-container">
                <button onClick={handleGoogleLogin} className="submit gray">Login with Google</button>
            </div>
            <div className="footer">
                Not a member? <Link to="/signup">Sign up here!</Link>
            </div>
        </div>
    );
};

export default UserLogin;