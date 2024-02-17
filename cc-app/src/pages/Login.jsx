import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import { FaUser } from "react-icons/fa";
import './LoginSignup.css'; // Ensure this path is correct

const backendUrl = process.env.REACT_APP_BACKEND_URL || 'http://localhost:8080';

const Login = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [values, setValues] = useState({
        email: '',
        password: '',
        name: '' // This is only for signup
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setValues({ ...values, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const endpoint = isLogin ? '/login' : '/register';
        const data = isLogin ? { email: values.email, password: values.password } : values;

        try {
            const response = await axios.post(`${backendUrl}${endpoint}`, data);
            localStorage.setItem('authToken', response.data.token); // Assuming token is returned
            navigate('/home');
        } catch (error) {
            console.error(`${isLogin ? 'Login' : 'Signup'} error:`, error.response);
            // Optionally set an error state here to display an error message
        }
    };

    const handleOAuth2Login = () => {
        window.location.href = `${backendUrl}/oauth2/authorization/google`; // Redirect to OAuth2 login
    };

    // Function to toggle between Login and Signup
    const toggleIsLogin = () => {
        setIsLogin(!isLogin);
        setValues({ email: '', password: '', name: '' }); // Reset form values
    };

    return (
        <div className="auth-container">
            <h2>{isLogin ? 'Login' : 'Signup'}</h2>
            <form onSubmit={handleSubmit} className="auth-form">
                {!isLogin && (
                    <div className="input-group">
                        <FaUser className="icon" />
                        <input
                            type="text"
                            placeholder="Full Name"
                            name="name"
                            value={values.name}
                            onChange={handleChange}
                            required
                        />
                    </div>
                )}
                <div className="input-group">
                    <MdEmail className="icon" />
                    <input
                        type="email"
                        placeholder="Email Address"
                        name="email"
                        value={values.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="input-group">
                    <RiLockPasswordFill className="icon" />
                    <input
                        type="password"
                        placeholder="Password"
                        name="password"
                        value={values.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" className="submit-btn">
                    {isLogin ? 'Login' : 'Sign Up'}
                </button>
                {isLogin && (
                    <button onClick={handleOAuth2Login} className="oauth-btn">
                        Login with Google
                    </button>
                )}
            </form>
            <div className="toggle-container">
                <button onClick={toggleIsLogin} className="toggle-btn">
                    {isLogin ? 'Need an account? Sign up' : 'Already have an account? Login'}
                </button>
            </div>
        </div>
    );
};

export default Login;