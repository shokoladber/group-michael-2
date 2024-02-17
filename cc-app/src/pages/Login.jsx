import React from 'react';
import UserLogin from '../Components/auth/UserLogin';
import { useNavigate } from 'react-router-dom';
import '../Components/auth/LoginSignup.css';

const Login = () => {
    const navigate = useNavigate();

    return (
        <div className="login-page">
            <h1>Login</h1>
            <UserLogin />
        </div>
    );
};

export default Login;
