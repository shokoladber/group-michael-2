// OAuth2RedirectHandler.jsx
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');
        const error = urlParams.get('error');

        if (token) {
            localStorage.setItem('authToken', token);
            navigate('/'); // Navigate to the home or dashboard page
        } else if (error) {
            // Log the error or display it to the user
            navigate('/login'); // Navigate back to login on error
        }
    }, [navigate]);

    return <div>Redirecting...</div>;
};

export default OAuth2RedirectHandler;
