// cc-app/src/Components/auth/UserLogin.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import InputField from './inputField'; // Import the new component
import './LoginSignup.css';

const backendUrl = process.env.REACT_APP_BACKEND_URL;

const UserLogin = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(""); // New state for error messages

    const handleLogin = async (e) => {
        e.preventDefault();
        setError(""); // Clear any existing errors
        try {
            const response = await axios.post(`${backendUrl}/api/auth/login`, { email, password });
            console.log(response.data);
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
                    <InputField
                        type="email"
                        placeholder="Email"
                        Icon={MdEmail}
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <InputField
                        type="password"
                        placeholder="Password"
                        Icon={RiLockPasswordFill}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                {error && <div className="error-message">{error}</div>} {/* Display error message */}
                <div className="submit-container">
                    <button type="submit" className="submit">Login</button>
                </div>
            </form>
        </div>
    );
};

export default UserLogin;