import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import { FaUser } from "react-icons/fa";
import './LoginSignup.css'; // Ensure this path is correct

const backendUrl = process.env.REACT_APP_BACKEND_URL;

const UserSignup = () => {
    const [formData, setFormData] = useState({
        email: '',
        password: '',
        name: '',
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSignup = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const response = await axios.post(`${backendUrl}/register`, formData);
            console.log(response.data);
            // Assuming the server responds with a token and user data
            localStorage.setItem('token', response.data.token);
            navigate('/home'); // Redirect to the home page or dashboard after signup
        } catch (error) {
            console.error("Signup error:", error);
            setError(error.response && error.response.data ? error.response.data.message : error.message);
        }
    };

    return (
        <div className="signup-container">
            <form onSubmit={handleSignup} className="auth-form">
                <div className="input-group">
                    <FaUser className="icon" />
                    <input
                        type="text"
                        placeholder="Full Name"
                        name="name"
                        value={formData.name}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="input-group">
                    <MdEmail className="icon" />
                    <input
                        type="email"
                        placeholder="Email Address"
                        name="email"
                        value={formData.email}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="input-group">
                    <RiLockPasswordFill className="icon" />
                    <input
                        type="password"
                        placeholder="Password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                {error && <div className="error-message">{error}</div>}
                <button type="submit" className="submit-btn">Sign Up</button>
            </form>
        </div>
    );
};

export default UserSignup;
