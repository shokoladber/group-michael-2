import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');
        const checkIfNewUser = async () => {
            if (token) {
                localStorage.setItem('authToken', token);  // Store the token for OAuth2

                try {
                    // Use axios directly
                    const response = await axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/check-new-user`, {
                        headers: {
                            Authorization: `Bearer ${token}`,
                        },
                    });
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
