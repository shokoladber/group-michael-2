import React, { useState } from 'react';
import UserLogin from './UserLogin';
import UserSignup from './UserSignup';
import './LoginSignup.css';

const LoginSignup = () => {
    const [isLogin, setIsLogin] = useState(true);

    return (
        <div className="login-signup-container">
            {isLogin ? (
                <>
                    <UserLogin />
                    <div className="toggle-action" onClick={() => setIsLogin(false)}>
                        Need an account? Sign up
                    </div>
                </>
            ) : (
                <>
                    <UserSignup />
                    <div className="toggle-action" onClick={() => setIsLogin(true)}>
                        Already have an account? Login
                    </div>
                </>
            )}
        </div>
    );
};

export default LoginSignup;
