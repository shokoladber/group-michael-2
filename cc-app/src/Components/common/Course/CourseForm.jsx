// src/Components/Course/CourseForm.js
import React, { useState } from 'react';

const CourseForm = ({ onSave }) => {
    const [course, setCourse] = useState({ name: '', description: '' });

    const handleChange = (e) => {
        setCourse({ ...course, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(course);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                name="name"
                placeholder="Course Name"
                value={course.name}
                onChange={handleChange}
                required
            />
            <textarea
                name="description"
                placeholder="Course Description"
                value={course.description}
                onChange={handleChange}
                required
            />
            <button type="submit">Save Course</button>
        </form>
    );
};

export default CourseForm;
