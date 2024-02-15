import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import { FaUser } from "react-icons/fa";
import './LoginSignup.css';

// Retrieve environment variables
const backendUrl = process.env.REACT_APP_BACKEND_URL;
const oauth2RedirectUri = process.env.REACT_APP_OAUTH2_REDIRECT_URI;
const oauth2Url = `${backendUrl}/oauth2/authorization/google`;

const LoginSignup = () => {
    const [action, setAction] = useState("Login");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const navigate = useNavigate();

    const handleRegularAuth = async (e) => {
        e.preventDefault();
        const url = action === "Login" ? `${backendUrl}/api/login` : `${backendUrl}/api/signup`;

        try {
            const response = await axios.post(url, {
                email,
                password,
                ...(action === "Signup" && { name })
            });
            console.log(response.data);
            // If login/signup is successful, you might want to save the token and navigate to the home page
            // localStorage.setItem('authToken', response.data.token);
            navigate('/home');
        } catch (error) {
            console.error("An error occurred:", error.response);
            // Handle errors (e.g., show error message)
        }
    };

    console.log("OAuth2 URL:", oauth2Url);


    const handleOAuth2Login = () => {
        window.location.href = `${backendUrl}/oauth2/authorization/google`;
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">{action}</div>
                <div className="underline"></div>
            </div>
            <form onSubmit={handleRegularAuth}>
                <div className="inputs">
                    {action === "Signup" && (
                        <div className="input">
                            <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
                            <FaUser />
                        </div>
                    )}
                    <div className="input">
                        <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        <MdEmail />
                    </div>
                    <div className="input">
                        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
                        <RiLockPasswordFill />
                    </div>
                </div>
                <div className="submit-container">
                    <button type="submit" className={action === "Login" ? "submit" : "submit gray"}>{action}</button>
                </div>
            </form>
            {action === "Login" && (
                <>
                    <div className="forgot-password">Lost Password?<span> Click Here!</span></div>
                    <div className="submit-container">
                        <button className="submit" onClick={handleOAuth2Login}>Login with Google</button>
                    </div>
                </>
            )}
            <div className="submit-container">
                <div className={action === "Login" ? "submit gray" : "submit"} onClick={() => setAction("Signup")}>Sign Up</div>
                <div className={action === "Signup" ? "submit gray" : "submit"} onClick={() => setAction("Login")}>Login</div>
            </div>
        </div>
    );
};

export default LoginSignup;
