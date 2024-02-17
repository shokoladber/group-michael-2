import React from 'react';
import axios from 'axios';
import './UserRoleSelection.css';
import { useNavigate } from 'react-router-dom';

const backendUrl = process.env.REACT_APP_BACKEND_URL;

const UserRoleSelection = () => {
    const navigate = useNavigate();

    const handleRoleSelect = (role) => {
        axios.post(`${backendUrl}/api/update-role`, { role })
            .then(response => {
                navigate(response.data.redirectUrl);
            })
            .catch(error => {
                console.error('Error while updating role:', error);
            });
    };


    return (
        <div>
            <h1>Select Your Role</h1>
            <button onClick={() => handleRoleSelect('PET_GUARDIAN')}>Pet Guardian</button>
            <button onClick={() => handleRoleSelect('PET_TRAINER')}>Pet Trainer</button>
        </div>
    );
};

export default UserRoleSelection;
