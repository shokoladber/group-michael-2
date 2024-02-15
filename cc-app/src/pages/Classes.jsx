// src/pages/CoursesPage.js
import React, { useState, useEffect } from 'react';
import CourseList from '../Components/Course/CourseList';
import CourseForm from '../Components/Course/CourseForm';
import { ClassroomService } from '../services/ClassroomService';

function Classes() {
    const [courses, setCourses] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchCourses = async () => {
        setLoading(true);
        try {
            const response = await ClassroomService.listCourses();
            setCourses(response.data);
            setError(null);
        } catch (err) {
            setError('Failed to fetch courses');
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    // Call fetchCourses when the component mounts
    useEffect(() => {
        fetchCourses();
    }, []);

    const handleCourseSubmit = async (courseData) => {
        try {
            const response = await ClassroomService.createCourse(courseData);
            setCourses([...courses, response.data]);
        } catch (err) {
            setError('Failed to create course');
            console.error(err);
        }
    };

    return (
        <div>
            <h1>Courses</h1>
            {error && <p className="error">{error}</p>}
            {loading ? (
                <p>Loading courses...</p>
            ) : (
                <CourseList courses={courses} />
            )}
            <h2>Add New Course</h2>
            <CourseForm onSubmit={handleCourseSubmit} />
        </div>
    );
}

export default Classes;
