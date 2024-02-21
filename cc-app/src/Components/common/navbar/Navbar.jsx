import { LiaAccusoft } from "react-icons/lia";
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Dropdown from '../dropdown/Dropdown'; // Import the Dropdown component
import './Navbar.css'; // Import CSS file for styling

function Navbar() {
  const [dropdownOpen, setDropdownOpen] = useState(false);

  const toggleDropdown = () => {
    setDropdownOpen(!dropdownOpen);
  };

  return (
    <nav className='navbar'>

      <div className='navbar-container'>
        <Link to='/' className='navbar__logo'> <LiaAccusoft />  Canine Coach</Link>
        <div className='menu__icon' onClick={toggleDropdown}>
          <i className='fas fa-bars'></i>
        </div>

        <ul className={dropdownOpen ? 'nav__menu active' : 'nav__menu'}>

          <li className='nav__item'>
            <Link to="/" className='nav__links' onClick={() => setDropdownOpen(false)}>Home</Link>
          </li>

          <li className='nav__item'>
            <Link to="/courses" className='nav__links' onClick={() => setDropdownOpen(false)}>Courses <i className='fas fa-caret-down' /></Link>
            {/* <Dropdown /> */}
          </li>

          <li className='nav__item'>
            <Link to="/blog" className='nav__links' onClick={() => setDropdownOpen(false)}>Blog</Link>
          </li>

          <li className='nav__item'>
            <Link to="/petProfile" className='nav__links' onClick={() => setDropdownOpen(false)}>Pet Profile</Link>
          </li>

          <li className='nav__item'>
            <Link to="/contact" className='nav__links' onClick={() => setDropdownOpen(false)}>Contact</Link>
          </li>

          <li className='nav__item'>
            <Link to="/purchase" className='nav__links' onClick={() => setDropdownOpen(false)}>Purchase</Link>
          </li>

          <li className='nav__item'>
            <Link to="/login" className='nav__links' onClick={() => setDropdownOpen(false)}>Login</Link>
          </li>

        </ul>
      </div>
    </nav>
  );
}

export default Navbar;


  