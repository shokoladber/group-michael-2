import React, { useState } from 'react';
import './Search.css'; 
import { Link } from 'react-router-dom';

function Search() {
  const [query, setQuery] = useState('');

  const handleChange = (e) => {
    setQuery(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle search logic, e.g., perform search API request
    console.log('Searching for:', query);
  };

  return (
    <form className="search-form" onSubmit={handleSubmit}>
      <input
        type="text"
        value={query}
        onChange={handleChange}
        placeholder="Search..."
        className="search-input"
      />
      {/* <li className='nav__item'>
            <Link to="/sign up" className='header-input__1'><button>Sign Up</button></Link>
          </li> */}
      <Link to="/search" className='search-input__1'><button type="submit" className="search-button">
        Search
      </button></Link>
    </form>
  );
}

export default Search;
