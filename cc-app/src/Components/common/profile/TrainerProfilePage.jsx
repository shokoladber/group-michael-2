import React, { useState, useEffect } from 'react';
import api from '../../../utils/api'; // Adjust the path as necessary
import './ProfileStyling.css'; // Make sure to have this CSS file

function TrainerProfilePage() {
    const [trainer, setTrainer] = useState({
        name: '',
        specialties: '',
        description: '',
    });
    const [isEditing, setIsEditing] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await api.get('/trainer-profile');
                setTrainer(response.data);
            } catch (error) {
                console.error('Error fetching trainer data:', error);
                setErrorMessage(error.response?.data?.message || 'Failed to fetch trainer data. Please try again.');
            }
        }
        fetchData();
    }, []);

    const handleChange = (e) => {
        setTrainer({ ...trainer, [e.target.name]: e.target.value });
    };

    const handleUpdateSubmit = async (e) => {
        e.preventDefault();
        setErrorMessage('');
        try {
            await api.put('/api/trainer-profiles', trainer);
            setIsEditing(false);
        } catch (error) {
            console.error('Error updating trainer data:', error);
            setErrorMessage(error.response?.data?.message || 'Failed to update trainer profile. Please try again.');
        }
    };

    return (
        <div className="trainer-profile-container">
            {isEditing ? (
                <form onSubmit={handleUpdateSubmit} className="edit-form">
                    <label htmlFor="name">Name:</label>
                    <input
                        id="name"
                        type="text"
                        name="name"
                        value={trainer.name}
                        onChange={handleChange}
                        className="input"
                        required
                    />
                    <label htmlFor="specialties">Specialties:</label>
                    <textarea
                        id="specialties"
                        name="specialties"
                        value={trainer.specialties}
                        onChange={handleChange}
                        className="input"
                        required
                    />
                    <label htmlFor="description">Description:</label>
                    <textarea
                        id="description"
                        name="description"
                        value={trainer.description}
                        onChange={handleChange}
                        className="input"
                        required
                    />
                    {errorMessage && <div className="error-message">{errorMessage}</div>}
                    <div className="form-buttons">
                        <button type="submit" className="button">Save</button>
                        <button type="button" onClick={() => setIsEditing(false)} className="button">Cancel</button>
                    </div>
                </form>
            ) : (
                <>
                    <h1>{trainer.name}</h1>
                    <p>Specialties: {trainer.specialties}</p>
                    <p>Description: {trainer.description}</p>
                    <button onClick={() => setIsEditing(true)} className="button">Edit Profile</button>
                </>
            )}
            {errorMessage && !isEditing && <div className="error-message">{errorMessage}</div>}
        </div>
    );
}

export default TrainerProfilePage;
