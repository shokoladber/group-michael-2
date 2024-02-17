const mysql = require('mysql2');

// Create a connection pool
const pool = mysql.createPool({
  host: '127.0.0.1', // Replace with your MySQL host
  user: 'root', // Replace with your MySQL username
  password: 'dancer', // Replace with your MySQL password
  database: 'cc-app', // Replace with your MySQL database name
  waitForConnections: true,
  connectionLimit: 10, // Adjust the connection limit based on your requirements
  queueLimit: 0 // No limit on the number of connections in the queue
});

module.exports = pool;
