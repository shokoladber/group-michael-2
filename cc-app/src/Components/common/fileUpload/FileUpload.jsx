import React, { useState } from 'react';
import './FileUpload.css';

function FileUpload() {
  const [dogInfo, setDogInfo] = useState({
    name: '',
    breed: '',
    age: '',
    photo: null
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDogInfo(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handlePhotoChange = (e) => {
    const file = e.target.files[0];
    setDogInfo(prevState => ({
      ...prevState,
      photo: file
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle form submission, such as sending data to server or processing the data
    console.log('Submitted dog info:', dogInfo);
  };

  return (
    <div className="file-upload-container">
      <h2>Upload Pet Profile</h2>
      <form onSubmit={handleSubmit} className="upload-form">
        <div className="form-group">
          <label htmlFor="name">Name:</label>
          <input type="text" id="name" name="name" value={dogInfo.name} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="breed">Breed:</label>
          <input type="text" id="breed" name="breed" value={dogInfo.breed} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="age">Age:</label>
          <input type="text" id="age" name="age" value={dogInfo.age} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label htmlFor="photo">Photo:</label>
          <input type="file" id="photo" name="photo" accept="image/*" onChange={handlePhotoChange} />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default FileUpload;
