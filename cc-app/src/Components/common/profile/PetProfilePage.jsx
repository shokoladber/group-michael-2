// PetProfilePage.jsx
import React, { useState } from 'react';
import api from '../../../utils/api';
import { useNavigate } from 'react-router-dom';
import './ProfileStyling.css';

const PetProfilePage = () => {
    const [petProfile, setPetProfile] = useState({
        name: '',
        type: '',
        age: '',
        description: '',
    });
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setPetProfile({ ...petProfile, [name]: value });
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        setErrorMessage('');
        try {

            await api.put(`/api/pet-profiles/${petProfile.id}`, petProfile);
            navigate('/home');
        } catch (error) {
            setErrorMessage(error.response?.data?.message || 'Failed to update the pet profile. Please try again.');
        }
    };

    return (
        <div className="container">
            <div className="header">
                <div className="text">Update Your Pet Profile</div>
                <div className="underline"></div>
            </div>

            <form onSubmit={handleFormSubmit}>
                <div className="inputs">
                    <div className="input">
                        <input type="text" name="name" placeholder="Pet's Name" onChange={handleInputChange} value={petProfile.name} required />
                    </div>
                    <div className="input">
                        <input type="text" name="type" placeholder="Pet's Type" onChange={handleInputChange} value={petProfile.type} required />
                    </div>
                    <div className="input">
                        <input type="number" name="age" placeholder="Pet's Age" onChange={handleInputChange} value={petProfile.age} required />
                    </div>
                    <div className="input">
                        <textarea name="description" placeholder="Description" onChange={handleInputChange} value={petProfile.description} />
                    </div>
                </div>

                {errorMessage && <div className="error-message">{errorMessage}</div>}

                <div className="submit-container">
                    <button type="submit" className="submit">Update Pet Profile</button>
                </div>
            </form>
        </div>
    );
};

export default PetProfilePage;
