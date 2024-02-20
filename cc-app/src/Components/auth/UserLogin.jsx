import React, { useState } from 'react';
// Replace direct axios import with custom instance
import api from '../../utils/api'; // Update the path according to where you place your api.js file
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignup.css';

const UserLogin = () => {
    const [credentials, setCredentials] = useState({ email: '', password: '' });
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setCredentials({ ...credentials, [name]: value });
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        setErrorMessage('');
        try {
            // Use the custom Axios instance for API calls
            const response = await api.post('/api/auth/login', credentials);
            localStorage.setItem('auth_token', response.data.token); // Ensure you use the same key as in api.js
            navigate('/home');
        } catch (error) {
            const defaultErrorMessage = 'Login failed. Please check your credentials and try again.';
            setErrorMessage(error.response?.data?.message || defaultErrorMessage);
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

            <form onSubmit={handleFormSubmit}>
                <div className="inputs">
                    <div className="input">
                        <input type="email" name="email" placeholder="Email Address" onChange={handleInputChange} value={credentials.email} required />
                    </div>
                    <div className="input">
                        <input type="password" name="password" placeholder="Password" onChange={handleInputChange} value={credentials.password} required />
                    </div>
                </div>
                {errorMessage && <div className="error-message">{errorMessage}</div>}
                <div className="submit-container">
                    <button type="submit" className="submit">Login</button>
                    <button type="button" onClick={handleGoogleLogin} className="submit gray">Login with Google</button>
                </div>
            </form>
            <div className="footer">
                Not a member? <Link to="/signup">Sign up here!</Link>
            </div>
        </div>
    );
};

export default UserLogin;
