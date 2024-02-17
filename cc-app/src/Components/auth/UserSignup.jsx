import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import { FaUser } from "react-icons/fa";
import './LoginSignup.css';

const backendUrl = process.env.REACT_APP_BACKEND_URL;

const UserSignup = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [error, setError] = useState("");

    const handleSignup = async (e) => {
        e.preventDefault();
        setError("");
        try {
            const response = await axios.post(`${backendUrl}/api/auth/signup`, {
                name,
                email,
                password
            });
            // Assuming that the sign-up was successful, navigate to the role selection page
            navigate('/select-role');
        } catch (error) {
            setError("Signup error: " + (error.response?.data?.error || "An unexpected error occurred"));
        }
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">Sign Up</div>
                <div className="underline"></div>
            </div>
            <form onSubmit={handleSignup}>
                <div className="inputs">
                    <div className="input">
                        <FaUser className="input-icon" />
                        <input
                            type="text"
                            placeholder="Full Name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                    </div>
                    <div className="input">
                        <MdEmail className="input-icon" />
                        <input
                            type="email"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="input">
                        <RiLockPasswordFill className="input-icon" />
                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                </div>
                {error && <div className="error-message">{error}</div>}
                <div className="submit-container">
                    <button type="submit" className="submit">Sign Up</button>
                </div>
            </form>
            <button onClick={() => navigate('/oauth2/redirect')} className="oauth-button">
                Register with Google
            </button>
            <div className="link-container">
                <span className="link-text" onClick={() => navigate('/login')}>Already a member? Log in here!</span>
            </div>
        </div>
    );
};

export default UserSignup;
