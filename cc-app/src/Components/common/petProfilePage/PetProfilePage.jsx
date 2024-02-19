import React, { useState, useEffect } from "react";
import axios from 'axios';
import './PetProfilePage.css'; 

function PetProfilePage() {
  const [pet, setPet] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    age: '',
    breed: '',
    description: '',
  });

  useEffect(() => {
    fetchPetData();
  }, []);

  const fetchPetData = async () => {
    try {
      const response = await axios.get('/api/pet/123');
      setPet(response.data);
    } catch (error) {
      console.error('Error fetching pet data:', error);
    }
  };

  const handleEditSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put('/api/pet/123', formData);
      setPet(formData);
      setIsEditing(false);
    } catch (error) {
      console.error('Error updating pet data:', error);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className="pet-profile-container">
      {pet ? (
        <div className="pet-profile">
          <h1>{pet.name}</h1>
          <p>Age: {pet.age}</p>
          <p>Age: {pet.age}</p>
          <p>Breed: {pet.breed}</p>
          <p>Description: {pet.description}</p>
          {isEditing ? (
            <form onSubmit={handleEditSubmit} className="edit-form">
              <input
                type="text"
                name="age"
                placeholder="Age"
                value={formData.age}
                onChange={handleChange}
                className="input"
              />
              <input
                type="text"
                name="breed"
                placeholder="Breed"
                
                value={formData.breed}
                onChange={handleChange}
                className="input"
              />
              <input
                type="text"
                name="description"
                placeholder="Description"
                value={formData.description}
                onChange={handleChange}
                className="input"
              />
              <button type="submit" className="button">Save</button>
              <button onClick={() => setIsEditing(false)} className="button">Cancel</button>
            </form>
          ) : (
            <button onClick={() => setIsEditing(true)} className="button">Edit</button>
          )}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  )
}

export default PetProfilePage;

