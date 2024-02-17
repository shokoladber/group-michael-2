// cc-app/src/Components/auth/UserSignup.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import { FaUser } from "react-icons/fa";
import InputField from './InputField'; // Assuming InputField is in the auth folder
import './LoginSignup.css';

const backendUrl = process.env.REACT_APP_BACKEND_URL;

const UserSignup = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [error, setError] = useState(""); // State for error messages

    const handleSignup = async (e) => {
        e.preventDefault();
        setError(""); // Clear any existing errors
        try {
            const response = await axios.post(`${backendUrl}/api/auth/signup`, {
                email,
                password,
                name
            });
            console.log(response.data);
            navigate('/home');
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
                    <InputField
                        type="text"
                        placeholder="Name"
                        Icon={FaUser}
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
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
                    <button type="submit" className="submit">Sign Up</button>
                </div>
            </form>
        </div>
    );
};

export default UserSignup;