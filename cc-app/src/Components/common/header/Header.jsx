import React from "react";
import './Header.css';
import pix1 from '../../Assets/px5.jpg';
import pix2 from '../../Assets/px6.jpg';
import Search from "../../common/search/Search";
import { Link } from "react-router-dom";


function Header() {
  return (
    <div className="header section__padding" id='home'>
      <div className="header-content">
       <Search />
        <h1 className="header-title">Unleash Your Dog's Potential with Canine Coach</h1>
        <p className="header-subtitle">Transforming Paws into Well-Behaved Champions!</p>
        
        <div className="header-input">
          {/* <input type="email" placeholder="Enter Your Email Address" /> */}
          <li className='nav__item'>
            <Link to="/sign up" className='header-input__1'><button>Sign Up</button></Link>
          </li>
          
        </div>

        <div className="header-trainers">
          <img src={pix2} alt="Trusted Trainers" className="header-trainer-img" />
          <p>Trusted Trainers</p>
        </div>
      </div>
      
      <div className="header-image">
        <img src={pix1} alt='' className="header-img" />
      </div>
    </div>
  );
}

export default Header;


