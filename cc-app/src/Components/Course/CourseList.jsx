// src/Components/Course/CourseList.jsx
import React, { useEffect, useState } from 'react';
import { ClassroomService } from '../../services/ClassroomService';
import axios from "axios";

function CourseList() {
    const [courses, setCourses] = useState([]);
    const [editingCourse, setEditingCourse] = useState(null);

    useEffect(() => {
        ClassroomService.listCourses().then(response => {
            setCourses(response.data);
        }).catch(error => {
            console.error("Error fetching courses:", error);
        });
    }, []);

    const handleDelete = (courseId) => {
        ClassroomService.deleteCourse(courseId).then(() => {
            setCourses(courses.filter(course => course.id !== courseId));
        }).catch(error => {
            console.error("Error deleting course:", error);
        });
    };

    const handleEdit = (course) => {
        setEditingCourse(course);
    };

    const handleSave = async (course) => {
        try {
            const updatedCourse = await ClassroomService.updateCourse(course.id, course);
            setCourses(courses.map(c => c.id === course.id ? updatedCourse.data : c));
            setEditingCourse(null); // Reset the editing state
        } catch (error) {
            console.error("Error updating course:", error);
        }
    };

    const handleCancel = () => {
        setEditingCourse(null);
    };

    return (
        <div>
            {courses.map((course) => (
                <div key={course.id}>
                    <h3>{course.name}</h3>
                    <p>{course.description}</p>
                    {editingCourse && editingCourse.id === course.id ? (
                        <div>
                            {/* Inline editing form */}
                            <input
                                type="text"
                                value={editingCourse.name}
                                onChange={(e) => setEditingCourse({ ...editingCourse, name: e.target.value })}
                            />
                            <button onClick={() => handleSave(editingCourse)}>Save</button>
                            <button onClick={handleCancel}>Cancel</button>
                        </div>
                    ) : (
                        <div>
                            <button onClick={() => handleEdit(course)}>Edit</button>
                            <button onClick={() => handleDelete(course.id)}>Delete</button>
                        </div>
                    )}
                </div>
            ))}
        </div>


);

}

export default CourseList;
