import React, { useEffect, useState } from 'react';
import './Navbar.css';
import { Link } from 'react-router-dom';
// import Button from '../button'; // Uncomment if you have a Button component
import { LiaAccusoft } from "react-icons/lia";
import Dropdown from '../dropdown/Dropdown';

function Navbar() {
    const [click, setClick] = useState(false);
    const [button, setButton] = useState(true); // Assuming you want the button to be shown by default
    const [dropdown, setDropdown] = useState(false);

    const handleClick = () => setClick(!click);
    const closeMobileMenu = () => setClick(false);

    const showButton = () => {
        if (window.innerWidth <= 960) {
            setButton(false);
        } else {
            setButton(true);
        }
    };

    useEffect(() => {
        showButton();
        // Cleanup the event listener on component unmount
        const handleResize = () => showButton();
        window.addEventListener('resize', handleResize);

        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    return (
        <nav className='navbar'>
            <div className='navbar-container'>
                <Link to='/' className='navbar__logo' onClick={closeMobileMenu}>
                    <LiaAccusoft /> CC
                </Link>

                <div className='menu__icon' onClick={handleClick}>
                    <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
                </div>

                <ul className={click ? 'nav__menu active' : 'nav__menu'}>
                    <li className='nav__item'>
                        <Link to="/" className='nav__links' onClick={closeMobileMenu}>Home</Link>
                    </li>
                    <li className='nav__item' onMouseEnter={() => setDropdown(true)} onMouseLeave={() => setDropdown(false)}>
                        <Link to="/Classes" className='nav__links' onClick={closeMobileMenu}>
                            Classes <i className='fas fa-caret-down' />
                        </Link>
                        {dropdown && <Dropdown />}
                    </li>
                    <li className='nav__item'>
                        <Link to="/blog" className='nav__links' onClick={closeMobileMenu}>Blog</Link>
                    </li>
                    <li className='nav__item'>
                        <Link to="/contact" className='nav__links' onClick={closeMobileMenu}>Contact</Link>
                    </li>
                    <li className='nav__item'>
                        <Link to="/login" className='nav__links' onClick={closeMobileMenu}>Login</Link>
                    </li>
                    {/* <li className='nav__item'>
                        <Link to="/signup" className='nav__links' onClick={closeMobileMenu}>SignUp</Link>
                    </li> */}
                </ul>

                {/* Button components can be added here if needed */}
                {/* {button && <Button buttonStyle='btn--primary'>Login</Button>} */}
                {/* {button && <Button buttonStyle='btn--outline'>SignUp</Button>} */}
            </div>
        </nav>
    );
}

export default Navbar;
