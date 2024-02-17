import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import './LoginSignup.css';

const backendUrl = process.env.REACT_APP_BACKEND_URL;

const UserLogin = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();
        setError("");
        try {
            const response = await axios.post(`${backendUrl}/api/auth/login`, { email, password });
            navigate('/home');
        } catch (error) {
            setError("Login error: " + (error.response?.data?.error || "An unexpected error occurred"));
        }
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">Login</div>
                <div className="underline"></div>
            </div>
            <form onSubmit={handleLogin}>
                <div className="inputs">
                    <div className="input">
                        <MdEmail className="input-icon" />
                        <input
                            type="email"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <div className="input">
                        <RiLockPasswordFill className="input-icon" />
                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                </div>
                {error && <div className="error-message">{error}</div>}
                <div className="submit-container">
                    <button type="submit" className="submit">Login</button>
                </div>
            </form>
            <button onClick={() => navigate('/oauth2/redirect')} className="oauth-button">
                Login with Google
            </button>
            <div className="link-container">
                <span className="link-text" onClick={() => navigate('/signup')}>Not a member? Sign up here!</span>
            </div>
        </div>
    );
};

export default UserLogin;
