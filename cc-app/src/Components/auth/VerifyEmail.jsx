import React, { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import api from '../../utils/api';

const VerifyEmail = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const [status, setStatus] = useState('verifying');

    useEffect(() => {
        const verifyEmail = async () => {
            const params = new URLSearchParams(location.search);
            const token = params.get('token');

            if (!token) {
                setStatus('failure');
                return;
            }

            try {
                await api.get(`/api/auth/verify-email`, { params: { token } });

                // Update the user's verified status
                setStatus('success');
                setTimeout(() => navigate('/select-role'), 3000); // Redirect after 3 seconds to a success page
            } catch (error) {
                // Failed to verify the email
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
