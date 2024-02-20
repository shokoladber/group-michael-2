import React, { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import api from '../../utils/api'; // Adjust the path as necessary

const VerifyEmail = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const [status, setStatus] = useState('verifying'); // Possible states: 'verifying', 'success', 'failure'

    useEffect(() => {
        const verifyEmail = async () => {
            const params = new URLSearchParams(location.search);
            const token = params.get('token');

            if (!token) {
                setStatus('failure');
                return; // Exit early if no token is present
            }

            try {
                // Use the Axios instance to make the API call
                await api.get(`/api/auth/verify-email`, { params: { token } });

                // If the request is successful, update the status and navigate accordingly
                setStatus('success');
                setTimeout(() => navigate('/verification-success'), 3000); // Redirect after 3 seconds to a success page
            } catch (error) {
                // If there's an error, update the status and navigate accordingly
                setStatus('failure');
                setTimeout(() => navigate('/verification-failed'), 3000); // Redirect after 3 seconds to a failure page
            }
        };

        verifyEmail();
    }, [navigate, location.search]);

    return (
        <div>
            {status === 'verifying' && <h2>Verifying your email...</h2>}
            {status === 'success' && (
                <div>
                    <h2>Verification successful!</h2>
                    <p>You will be redirected shortly.</p>
                </div>
            )}
            {status === 'failure' && (
                <div>
                    <h2>Verification failed.</h2>
                    <p>Please ensure the link is correct or contact support for assistance.</p>
                    <p>You will be redirected to the home page shortly.</p>
                </div>
            )}
        </div>
    );
};

export default VerifyEmail;