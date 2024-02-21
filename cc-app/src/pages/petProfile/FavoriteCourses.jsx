import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FavoritesList = ({ userId }) => {
    const [favoriteCourses, setFavoriteCourses] = useState([]);

    useEffect(() => {

        const fetchFavoriteCourses = async () => {
            try {
                const response = await axios.get(`/api/favorites/list?userId=${userId}`);
                setFavoriteCourses(response.data);
            } catch (error) {
                console.error('Error fetching favorite courses:', error);
            }
        };

        fetchFavoriteCourses();
    }, [userId]);

    return (
        <div>
            <h2>Favorite Courses</h2>
            <ul>
                {favoriteCourses.map(course => (
                    <li key={course.id}>{course.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default FavoritesList;
