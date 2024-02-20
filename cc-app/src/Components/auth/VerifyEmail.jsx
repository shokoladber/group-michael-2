import React, { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

const VerifyEmail = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const [status, setStatus] = useState('verifying'); // 'verifying', 'success', or 'failure'

    useEffect(() => {
        const verifyEmail = async () => {
            const params = new URLSearchParams(location.search);
            const token = params.get('token');

            try {
                // Assuming you have set up an API helper or use axios directly
                // Replace with your actual API call to verify the token
                const response = await fetch(`${process.env.REACT_APP_BACKEND_URL}/api/user/verify-email?token=${token}`, {
                    method: 'GET'
                });

                if (response.ok) {
                    setStatus('success');
                    setTimeout(() => navigate('/select-role'), 10000); // Redirect after 10 seconds
                } else {
                    setStatus('failure');
                    setTimeout(() => navigate('/home'), 10000); // Redirect after 10 seconds
                }
            } catch (error) {
                setStatus('failure');
                setTimeout(() => navigate('/home'), 10000); // Redirect after 10 seconds
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
                    <p>You will be redirected to select your role shortly.</p>
                </div>
            )}
            {status === 'failure' && (
                <div>
                    <h2>Verification failed.</h2>
                    <p>You will be redirected to the home page shortly.</p>
                </div>
            )}
        </div>
    );
};

export default VerifyEmail;
