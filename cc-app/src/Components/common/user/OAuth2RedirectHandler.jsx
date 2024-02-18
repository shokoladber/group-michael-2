import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');
        const isNewUser = urlParams.get('isNewUser') === 'true';

        if (token) {
            // Store the token in local storage
            localStorage.setItem('authToken', token);

            // Redirect based on whether the user is new or returning
            if (isNewUser) {
                navigate('/select-role');
            } else {
                navigate('/home');
            }
        } else {
            // Redirect to login page
            navigate('/login');
        }
    }, [navigate]);

    // Loading indicator until redirect is complete
    return <div>Loading...</div>;
};

export default OAuth2RedirectHandler;
