import React, { useState } from 'react';
import api from '../../utils/api';
import { Link, useNavigate } from 'react-router-dom';
import './LoginSignup.css';

const UserSignup = () => {
    const [userDetails, setUserDetails] = useState({
        name: '',
        email: '',
        password: '',
        confirmPassword: '',
    });
    const [errorMessage, setErrorMessage] = useState(null);
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setUserDetails({ ...userDetails, [name]: value });
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        // Check if passwords match
        if (userDetails.password !== userDetails.confirmPassword) {
            setErrorMessage('Passwords do not match.');
            return;
        }
        try {
            await api.post('/api/auth/signup', {
                name: userDetails.name,
                email: userDetails.email,
                password: userDetails.password,
            });
            alert('Signup successful! Please check your email to verify your account.');
            navigate('/login');
        } catch (error) {
            const defaultErrorMessage = 'There was a problem with the signup process. Please try again later.';
            setErrorMessage(error.response?.data?.message || defaultErrorMessage);
        }
    };

    const handleGoogleSignUp = () => {
        window.location.href = `${process.env.REACT_APP_BACKEND_URL}/oauth2/authorization/google`;
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">Sign Up</div>
                <div className="underline"></div>
            </div>

            <form onSubmit={handleFormSubmit}>
                <div className="inputs">
                    <div className="input">
                        <input
                            type="text"
                            name="name"
                            placeholder="Full Name"
                            onChange={handleInputChange}
                            value={userDetails.name}
                            required
                        />
                    </div>
                    <div className="input">
                        <input
                            type="email"
                            name="email"
                            placeholder="Email Address"
                            onChange={handleInputChange}
                            value={userDetails.email}
                            required
                        />
                    </div>
                    <div className="input">
                        <input
                            type="password"
                            name="password"
                            placeholder="Password"
                            onChange={handleInputChange}
                            value={userDetails.password}
                            required
                        />
                    </div>
                    <div className="input">
                        <input
                            type="password"
                            name="confirmPassword"
                            placeholder="Confirm Password"
                            onChange={handleInputChange}
                            value={userDetails.confirmPassword}
                            required
                        />
                    </div>
                </div>
                {errorMessage && <div className="error-message">{errorMessage}</div>}
                <div className="submit-container">
                    <button type="submit" className="submit">Sign Up</button>
                    <button type="button" onClick={handleGoogleSignUp} className="submit gray">Sign Up with Google</button>
                </div>
            </form>
            <div className="footer">
                Already a member? <Link to="/login">Log in here!</Link>
            </div>
        </div>
    );
};

export default UserSignup;
