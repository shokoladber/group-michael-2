import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './LoginSignup.css';

const UserLogin = () => {
    const [credentials, setCredentials] = useState({ email: '', password: '' });
    const [error, setError] = useState(''); // Added error state
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setCredentials({ ...credentials, [name]: value });
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        try {
            const apiUrl = `${process.env.REACT_APP_BACKEND_URL}/api/auth/login`;
            const response = await axios.post(apiUrl, credentials);
            localStorage.setItem('authToken', response.data.token)
            navigate('/home');
        } catch (error) {
            console.error("Login error:", error.response ? error.response.data : error.message);
            setError(error.response ? error.response.data : 'An error occurred during login.');
        }
    };


    const handleGoogleLogin = () => {
        window.location.href = `${process.env.REACT_APP_BACKEND_URL}/oauth2/authorization/google`;
    };


    return (
        <div className="container">
            <div className="header">
                <div className="text">Login</div>
                <div className="underline"></div>
            </div>
            {error && <div className="error-message">{error}</div>}
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