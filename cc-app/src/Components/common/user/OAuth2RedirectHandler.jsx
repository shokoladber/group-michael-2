import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();

    useEffect(() => {
        // Extract the token from the URL fragment or query parameters
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');
        const isNewUser = urlParams.get('isNewUser') === 'true';

        if (token) {
            // Store the token in localStorage or context
            localStorage.setItem('authToken', token);

            // Redirect based on whether the user is new or returning
            if (isNewUser) {
                // Update to match your React route for role selection
                navigate('/select-role');
            } else {
                // Update to match your React route for home
                navigate('/home');
            }
        } else {
            // Handle error or missing token
            // Update to match your React route for login
            navigate('/login');
        }
    }, [navigate]);

    // Render a loading indicator until redirect is complete
    return <div>Loading...</div>;
};

export default OAuth2RedirectHandler;
