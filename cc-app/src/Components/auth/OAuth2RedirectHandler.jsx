import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const getAndStoreToken = async () => {
            try {
                // Make an API call to get the token from the session
                const response = await axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/auth/retrieve-token`);

                const { token, isNewUser } = response.data;
                localStorage.setItem('authToken', token); // Store the token

                // Navigate based on the user being new or existing.
                if (isNewUser) {
                    navigate('/select-role');
                } else {
                    navigate('/home');
                }
            } catch (error) {
                console.error('Error during token retrieval:', error);
                navigate('/login'); // Redirect to login on failure
            }
        };

        getAndStoreToken();
    }, [navigate]);

    // Render a loading spinner or a blank page while processing
    return <div>Loading...</div>;
};

export default OAuth2RedirectHandler;