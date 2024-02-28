import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './UserRoleSelection.css';

axios.defaults.baseURL = 'http://localhost:8080';

const UserRoleSelection = () => {
    const [selectedRole, setSelectedRole] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleRoleChange = (event) => {
        setSelectedRole(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        setIsLoading(true);
        setError(null);

        try {
            // Submit the selected role to the backend
            await axios.post('/api/user/select-role', { role: selectedRole });
            // Navigate to the corresponding profile creation page based on the role
            if (selectedRole === 'PET_GUARDIAN') {
                navigate('/pet-profile'); // Adjust as per your route setup
            } else if (selectedRole === 'PET_TRAINER') {
                navigate('/trainer-profile'); // Adjust as per your route setup
            }
        } catch (error) {
            console.error('Error updating the role:', error);
            setError('Failed to update role. Please try again.');
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="user-role-selection">
            <h2>Select Your Role</h2>
            {error && <p className="error">{error}</p>}
            <form onSubmit={handleSubmit}>
                <label>
                    Role:
                    <select value={selectedRole} onChange={handleRoleChange} disabled={isLoading}>
                        <option value="">--Select Role--</option>
                        <option value="PET_GUARDIAN">Pet Guardian</option>
                        <option value="PET_TRAINER">Pet Trainer</option>
                    </select>
                </label>
                <button type="submit" disabled={isLoading || !selectedRole}>
                    Submit
                </button>
            </form>
        </div>
    );
};

export default UserRoleSelection;
