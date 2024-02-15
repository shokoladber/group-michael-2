
import React, { useEffect } from 'react';
import axios from 'axios';


const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const ClassroomService = {
    listCourses: () => {
        return axios.get(`${API_BASE_URL}/courses`);
    },
    createCourse: (course) => {
        return axios.post(`${API_BASE_URL}/courses`, course);
    },
    updateCourse: (courseId, courseData) => {
        return axios.patch(`${API_BASE_URL}/courses/${courseId}`, courseData);
    },
    deleteCourse: (courseId) => {
        return axios.delete(`${API_BASE_URL}/courses/${courseId}`);
    },
    // Add more methods as needed for your application
};
