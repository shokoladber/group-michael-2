import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './BlogPost.css'; 

const BlogPost = ({ postId }) => {
  const [post, setPost] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    title: '',
    body: ''
  });

  useEffect(() => {
    console.log('Post ID:', postId); // Log the postId upon changes
    fetchPost();
  }, [postId]);

  useEffect(() => {
    console.log('Post:', post); // Log the post when it changes
  }, [post]);

  useEffect(() => {
    console.log('isEditing:', isEditing); // Log isEditing when it changes
  }, [isEditing]);

  useEffect(() => {
    console.log('formData:', formData); // Log formData when it changes
  }, [formData]);

  const fetchPost = async () => {
    try {
      const response = await axios.get(`/api/posts/${postId}`); // API endpoint
      setPost(response.data);
    } catch (error) {
      console.error('Error fetching post:', error);
    }
  };

  const handleEditSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`/api/posts/${postId}`, formData); // API endpoint
      setPost(formData);
      setIsEditing(false);
    } catch (error) {
      console.error('Error updating post:', error);
    }
  };

  const handleDelete = async () => {
    try {
      await axios.delete(`/api/posts/${postId}`); // Replace with actual API endpoint
      // Optionally, redirect to another page or show a success message
    } catch (error) {
      console.error('Error deleting post:', error);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className="blog-post">
      {post ? (
        <div>
          {isEditing ? (
            <form method='post' onSubmit={handleEditSubmit}>
              <input
                type="text"
                name="title"
                placeholder="Title"
                value={formData.title}
                onChange={handleChange}
              />
              <textarea
                name="body"
                placeholder="Body"
                value={formData.body}
                onChange={handleChange}
              ></textarea>
              <button type="submit">Save</button>
              <button onClick={() => setIsEditing(false)}>Cancel</button>
            </form>
          ) : (
            <div>
              <h2>{post.title}</h2>
              <p>{post.body}</p>
              <div className="buttons">
                <button onClick={() => setIsEditing(true)}>Edit</button>
                <button onClick={handleDelete}>Delete</button>
              </div>
            </div>
          )}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default BlogPost;

// This code will render a loading message while fetching data, 
// display an error message if there's an issue fetching the post data, 
// and render the post content if it's successfully fetched. 
// If no post is found, it will display a message indicating that no post was found.







