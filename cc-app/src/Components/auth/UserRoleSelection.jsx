import React, { useState } from 'react';
import api from '../../utils/api';
import { useNavigate } from 'react-router-dom';
import './LoginSignup.css';

const UserRoleSelection = () => {
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const handleRoleSelection = async (role) => {
        try {
            await api.post('/api/user/select-role', { role });

            if (role === 'PET_GUARDIAN') {
                navigate('/profile/pet-guardian');
            } else if (role === 'PET_TRAINER') {
                navigate('/profile/pet-trainer');
            }
        } catch (error) {
            console.error("Role selection error:", error.response ? error.response.data : "An error occurred during role selection.");
            setError(error.response ? error.response.data.message : "An error occurred during role selection.");
        }
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">Select Your Role</div>
                <div className="underline"></div>
            </div>
            {error && <div className="error-message">{error}</div>}
            <div className="submit-container">
                <button onClick={() => handleRoleSelection('PET_GUARDIAN')} className="submit">Pet Guardian</button>
                <button onClick={() => handleRoleSelection('PET_TRAINER')} className="submit">Pet Trainer</button>
            </div>
        </div>
    );
};

export default UserRoleSelection;
