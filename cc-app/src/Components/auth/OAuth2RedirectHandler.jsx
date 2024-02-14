import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();

    useEffect(() => {

        const isNewUser = localStorage.getItem('newUser') === 'true';

        if (isNewUser) {
            navigate('/select-role');
        } else {
            navigate('/home');
        }
    }, [navigate]);

    return null;
};

export default OAuth2RedirectHandler;
