import React, {useEffect, useState} from 'react';
import './Navbar.css';
import {Link} from 'react-router-dom';
import {GiSittingDog} from "react-icons/gi";

function Navbar() {
    const [click, setClick] = useState(false);
    const [button, setButton] = useState(true);

    const handleLoginSignup = () => {
        window.location.href = `${process.env.REACT_APP_BACKEND_URL}/oauth2/authorization/google`;
    };


    const closeMobileMenu = () => setClick(false);

    useEffect(() => {
        const showButton = () => {
            if (window.innerWidth <= 960) {
                setButton(false);
            } else {
                setButton(true);
            }
        };

        showButton();
        window.addEventListener('resize', showButton);

        return () => window.removeEventListener('resize', showButton);
    }, []);

    return (
        <div className='navbar'>
            <h2><GiSittingDog /> Cc</h2>
            <ul className={click ? 'nav-menu active' : 'nav-menu'}>
                <li className='nav-item'>
                    <Link to="/" className='nav-links' onClick={closeMobileMenu}>Home</Link>
                </li>
                <li className='nav-item'>
                    <Link to="/services" className='nav-links' onClick={closeMobileMenu}>Services</Link>
                </li>
                <li className='nav-item'>
                    <Link to="/blog" className='nav-links' onClick={closeMobileMenu}>Blog</Link>
                </li>
                <li className='nav-item'>
                    <Link to="/contact" className='nav-links' onClick={closeMobileMenu}>Contact</Link>
                </li>
                {button && (
                    <li className='nav-item'>
                        <button className='btn btn--outline' onClick={handleLoginSignup}>Login/Signup</button>
                    </li>
                )}
            </ul>
        </div>
    );
}

export default Navbar;
