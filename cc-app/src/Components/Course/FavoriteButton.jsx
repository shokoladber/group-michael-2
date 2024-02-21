import React, { useState } from 'react';
import axios from 'axios';

const FavoriteButton = ({ courseId, userId }) => {
    const [isFavorite, setIsFavorite] = useState(false);

    const handleAddToFavorites = async () => {
        try {

            await axios.post('/api/favorites/add', { courseId, userId });
            setIsFavorite(true);
        } catch (error) {
            console.error('Error adding course to favorites:', error);
        }
    };

    return (
        <button onClick={handleAddToFavorites} disabled={isFavorite}>
            {isFavorite ? 'Added to Favorites' : 'Add to Favorites'}
        </button>
    );
};

export default FavoriteButton;
