import React, { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        // Extract the code from URL query parameters
        const urlParams = new URLSearchParams(location.search);
        const code = urlParams.get('code');
        const state = urlParams.get('state'); // State parameter can be used for additional validation if passed

        // If there's a code, exchange it for a token
        if (code) {
            axios.post('/api/oauth2/code/google', { code, state }) // Endpoint to exchange code for token
                .then(response => {
                    // Assuming backend returns a flag indicating if the user is new
                    const isNewUser = response.data.isNewUser;
                    localStorage.setItem('authToken', response.data.token);
                    if (isNewUser) {
                        navigate('/select-role');
                    } else {
                        navigate('/home');
                    }
                })
                .catch(error => {
                    console.error("Error exchanging code for token:", error);
                    navigate('/login'); // Redirect to login on error
                });
        } else {
            // No code in URL, navigate to login
            navigate('/login');
        }
    }, [navigate, location]);

    return null;
};

export default OAuth2RedirectHandler;
