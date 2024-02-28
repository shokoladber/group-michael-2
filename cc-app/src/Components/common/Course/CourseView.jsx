import React, { useEffect, useState } from 'react';
import { ClassroomService } from '../../../services/ClassroomService';

function CourseView({ user }) {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        ClassroomService.listCourses().then(response => {
            setCourses(response.data);
        });
    }, []);

    const handleEnroll = (courseId) => {
        ClassroomService.enrollInCourse(courseId, user.email).then(() => {
            alert('Enrolled successfully!');
        });
    };

    return (
        <div>
            {courses.map((course) => (
                <div key={course.id}>
                    <h3>{course.name}</h3>
                    <p>{course.description}</p>
                    <button onClick={() => handleEnroll(course.id)}>Enroll</button>
                </div>
            ))}
        </div>
    );
}
