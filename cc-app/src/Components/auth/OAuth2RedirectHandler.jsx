import React, { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        // Parse the query string for 'newUser'
        const searchParams = new URLSearchParams(location.search);
        const isNewUser = searchParams.get('newUser') === 'true';

        // Navigate based on the user being new or existing
        if (isNewUser) {
            navigate('/select-role');
        } else {
            navigate('/home');
        }
    }, [navigate, location]);

    // Render a loading spinner or a blank page while processing
    return <div>Loading...</div>;
};

export default OAuth2RedirectHandler;
