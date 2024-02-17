// Assuming this is placed in cc-app/src/Components/auth/InputField.jsx
import React from 'react';
import './LoginSignup.css'; // Ensure this has the styles for your input fields

const InputField = ({ type, placeholder, Icon, value, onChange }) => (
    <div className="input">
        <input
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange}
            className="input-field"
        />
        {Icon && <Icon className="input-icon" />}
    </div>
);

export default InputField;
