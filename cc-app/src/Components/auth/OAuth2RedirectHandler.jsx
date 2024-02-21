import React, { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {

        const searchParams = new URLSearchParams(location.search);
        const isNewUser = searchParams.get('newUser') === 'true';

        if (isNewUser) {
            navigate('/select-role');
        } else {
            navigate('/home');
        }
    }, [navigate, location]);

    return <div>Loading...</div>;
};

export default OAuth2RedirectHandler;
