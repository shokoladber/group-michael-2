// server.js

const express = require('express');
const app = express();
const PORT = process.env.PORT || 5000; // Use port 5000 or a port defined by the environment variable

// Define middleware to parse incoming JSON requests
app.use(express.json());

// Define a test route
app.get('/', (req, res) => {
  res.send('Welcome to the backend server!');
});

// Start the server
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

// server.js

// const express = require('express');
// const app = express();
// const PORT = process.env.PORT || 5000;

// Dummy data for demonstration purposes (replace with a database later)
let blogPosts = [
  { id: 1, title: 'First Post', body: 'This is the first blog post.' },
  { id: 2, title: 'Second Post', body: 'This is the second blog post.' },
];

// Middleware to parse incoming JSON requests
app.use(express.json());

// Get all blog posts
app.get('/api/posts', (req, res) => {
  res.json(blogPosts);
});

// Get a single blog post by ID
app.get('/api/posts/:id', (req, res) => {
  const postId = parseInt(req.params.id);
  const post = blogPosts.find(post => post.id === postId);
  if (!post) {
    return res.status(404).json({ error: 'Post not found' });
  }
  res.json(post);
});

// Create a new blog post
app.post('/api/posts', (req, res) => {
  const { title, body } = req.body;
  const newPost = { id: blogPosts.length + 1, title, body };
  blogPosts.push(newPost);
  res.status(201).json(newPost);
});

// Update an existing blog post
app.put('/api/posts/:id', (req, res) => {
  const postId = parseInt(req.params.id);
  const { title, body } = req.body;
  let post = blogPosts.find(post => post.id === postId);
  if (!post) {
    return res.status(404).json({ error: 'Post not found' });
  }
  post.title = title;
  post.body = body;
  res.json(post);
});

// Delete a blog post
app.delete('/api/posts/:id', (req, res) => {
  const postId = parseInt(req.params.id);
  const index = blogPosts.findIndex(post => post.id === postId);
  if (index === -1) {
    return res.status(404).json({ error: 'Post not found' });
  }
  blogPosts.splice(index, 1);
  res.status(204).send();
});

// Start the server
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

