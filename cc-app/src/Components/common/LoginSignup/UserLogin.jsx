import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import './LoginSignup.css';

const backendUrl = process.env.REACT_APP_BACKEND_URL || 'http://localhost:8080';

const UserLogin = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${backendUrl}/login`, { email, password });
            console.log(response.data);
            navigate('/home'); // Redirect to the home page after login
        } catch (error) {
            console.error("Login error:", error.response);
            // Handle login error, e.g., display a message to the user
        }
    };


    const handleOAuth2Login = () => {
        window.location.href = `${backendUrl}/oauth2/authorization/google`;
    };

    const handlePasswordRecovery = () => {
        // Implement password recovery logic or redirect
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">Login</div>
                <div className="underline"></div>
            </div>
            <form onSubmit={handleLogin} className="inputs">
                <div className="input">
                    <MdEmail />
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="input">
                    <RiLockPasswordFill />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className="submit-container">
                    <button type="submit" className="submit">Login</button>
                </div>
            </form>
            <div className="oauth-container">
                <button onClick={handleOAuth2Login} className="submit">Login with Google</button>
            </div>
            <div className="forgot-password" onClick={handlePasswordRecovery}>
                Lost Password? <span>Click Here!</span>
            </div>
            {/* Add other components or links as needed */}
        </div>
    );
};

export default UserLogin;
