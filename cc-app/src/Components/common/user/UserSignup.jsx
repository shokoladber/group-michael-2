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
            const apiUrl = `${process.env.REACT_APP_BACKEND_URL}/api/auth/signup`;
            await axios.post(apiUrl, userDetails);
            navigate('/select-role');
        } catch (error) {
            console.error("Signup error:", error.response ? error.response.data : error.message);
        }
    };


    const handleGoogleSignUp = () => {
        window.location.href = `${process.env.REACT_APP_BACKEND_URL}/oauth2/authorization/google`;    };

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
