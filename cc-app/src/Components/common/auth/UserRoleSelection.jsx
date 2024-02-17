// UserRoleSelection.jsx
import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './LoginSignup.css'; // Corrected path (assuming it is in the same directory)

const UserRoleSelection = () => {
    const navigate = useNavigate();

    const handleRoleSelection = async (role) => {
        try {
            // Assuming you have a token stored
            const config = {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('authToken')}`
                }
            };
            await axios.post('/api/update-role', { role }, config);
            navigate('/home'); // Navigate to the home or dashboard page
        } catch (error) {
            console.error("Role selection error:", error.response.data);
            // Handle role selection error here
        }
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">Select Your Role</div>
                <div className="underline"></div>
            </div>
            <div className="submit-container">
                <button onClick={() => handleRoleSelection('PET_GUARDIAN')} className="submit">Pet Guardian</button>
                <button onClick={() => handleRoleSelection('PET_TRAINER')} className="submit">Pet Trainer</button>
            </div>
        </div>
    );
};

export default UserRoleSelection;