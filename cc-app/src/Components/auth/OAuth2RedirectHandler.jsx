// OAuth2RedirectHandler.js
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import apiClient from '../../../services/apiClient';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');
        const checkIfNewUser = async () => {
            if (token) {
                localStorage.setItem('authToken', token);  // Store the token for OAuth2

                try {
                    const response = await apiClient.get('/api/user/check-new-user');
                    if (response.data.isNewUser) {
                        navigate('/select-role');
                    } else {
                        navigate('/home');
                    }
                } catch (error) {
                    navigate('/login');
                }
            } else {
                navigate('/login');
            }
        };

        checkIfNewUser();
    }, [navigate]);

    return <div>Loading...</div>;
};

export default OAuth2RedirectHandler;