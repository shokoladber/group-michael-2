// UserSignup.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './LoginSignup.css'; // Corrected path (assuming it is in the same directory)

const UserSignup = () => {
    const [userDetails, setUserDetails] = useState({
        name: '',
        email: '',
        password: ''
    });
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setUserDetails({ ...userDetails, [name]: value });
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        try {
            await axios.post('/api/auth/signup', userDetails);
            navigate('/login'); // Redirect to login after successful signup
        } catch (error) {
            console.error("Signup error:", error.response.data);
            // Handle signup error here
        }
    };

    const handleGoogleSignUp = () => {
        window.location.href = '/oauth2/authorization/google'; // Your Spring Boot redirect URI for Google OAuth
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
                        <input type="text" name="name" placeholder="Full Name" onChange={handleInputChange} />
                    </div>
                    <div className="input">
                        <input type="email" name="email" placeholder="Email Address" onChange={handleInputChange} />
                    </div>
                    <div className="input">
                        <input type="password" name="password" placeholder="Password" onChange={handleInputChange} />
                    </div>
                </div>
                <div className="submit-container">
                    <button type="submit" className="submit">Sign Up</button>
                </div>
            </form>
            <div className="submit-container">
                <button onClick={handleGoogleSignUp} className="submit gray">Sign Up with Google</button>
            </div>
            <div className="footer">
                Already a member? <Link to="/login">Log in here!</Link>
            </div>
        </div>
    );
};

export default UserSignup;