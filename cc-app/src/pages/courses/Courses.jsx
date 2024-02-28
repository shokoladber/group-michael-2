import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Courses.css';

function Courses() {
    // State variables
    const [courses, setCourses] = useState([]);
    const [newCourse, setNewCourse] = useState({ name: '', description: '' });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch courses when component mounts
    useEffect(() => {
        fetchCourses();
    }, []);

    // Function to fetch courses from the backend
    const fetchCourses = async () => {
        try {
            const response = await axios.get('/api/googleclassroom/courses');
            setCourses(response.data);
            setLoading(false);
        } catch (error) {
            console.error('Error fetching courses:', error);
            setError('Failed to fetch courses.');
            setLoading(false);
        }
    };

    // Function to handle course creation
    const handleCreateCourse = async () => {
        try {
            await axios.post('/api/googleclassroom/courses', newCourse);
            setNewCourse({ name: '', description: '' });
            fetchCourses(); // Refresh courses after creation
        } catch (error) {
            console.error('Error creating course:', error);
            setError('Failed to create course.');
        }
    };

    return (
        <div className="container">
            <h2 className="text">Google Classroom</h2>

            {/* Course creation form */}
            <div className="inputs">
                <h3>Create New Course</h3>
                <input
                    type="text"
                    placeholder="Course Name"
                    value={newCourse.name}
                    onChange={(e) => setNewCourse({...newCourse, name: e.target.value})}
                />
                <input
                    type="text"
                    placeholder="Course Description"
                    value={newCourse.description}
                    onChange={(e) => setNewCourse({...newCourse, description: e.target.value})}
                />
                <button className="submit" onClick={handleCreateCourse}>Create Course</button>
            </div>

            {/* Display courses */}
            <div>
                <h3>Courses</h3>
                {loading ? (
                    <p>Loading...</p>
                ) : error ? (
                    <p>{error}</p>
                ) : (
                    <ul>
                        {courses.map((course) => (
                            <li key={course.id}>
                                <strong>{course.name}</strong>: {course.description}
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
}

export default Courses;
