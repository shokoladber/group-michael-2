import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const PrivatePage = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [authError, setAuthError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const authToken = localStorage.getItem('authToken');

        if (!authToken) {
            navigate('/login');
            return;
        }

        axios.get(`${process.env.REACT_APP_BACKEND_URL}/validate-token`, {
            headers: {
                Authorization: `Bearer ${authToken}`
            }
        })
            .then(response => {
                // The token is valid
                setIsLoading(false);
            })
            .catch(error => {
                // The token is invalid or expired
                console.error('Authentication error:', error);
                setAuthError('You are not authorized. Please log in again.');
                navigate('/login');
            });
    }, [navigate]);

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (authError) {
        return <div>{authError}</div>;
    }

    return (
        <div>
            <h1>Private Page</h1>
            <p>This is a protected page. If you're seeing this, you're authenticated!</p>
            {/* More private content */}
        </div>
    );
};

export default PrivatePage;
