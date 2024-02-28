import React, { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';


const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        const searchParams = new URLSearchParams(location.search);
        const isNewUser = searchParams.get('newUser') === 'true';
        const profileComplete = searchParams.get('profileComplete') === 'true';

        if (isNewUser) {
            navigate('/select-role');
        } else if (!profileComplete) {
            // Redirect to profile creation page if the user's profile is not complete
            navigate('/select-role');
        } else {
            navigate('/home');
        }
    }, [navigate, location]);

    return <div>Loading...</div>;
};

export default OAuth2RedirectHandler;
