import React, { useEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const VerifyAccount = () => {
    const [searchParams] = useSearchParams();
    const [verificationStatus, setVerificationStatus] = useState('Verifying your account...');
    const navigate = useNavigate();

    useEffect(() => {
        const verifyAccount = async () => {
            try {
                const token = searchParams.get('token');
                const apiUrl = `${process.env.REACT_APP_BACKEND_URL}/api/auth/verify-account?token=${token}`;
                await axios.get(apiUrl);
                setVerificationStatus('Your account has been successfully verified! Redirecting...');
                setTimeout(() => navigate('/login'), 5000);
            } catch (error) {
                setVerificationStatus('Failed to verify account. The link may be expired or invalid.');
            }
        };
        verifyAccount();
    }, [searchParams, navigate]);

    return (
        <div className="container">
            <h2>Account Verification</h2>
            <p>{verificationStatus}</p>
        </div>
    );
};

export default VerifyAccount;
