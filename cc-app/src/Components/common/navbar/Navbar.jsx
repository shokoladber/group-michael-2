import React, { useEffect, useState } from 'react';
import './Navbar.css';
import {Link} from 'react-router-dom'
import Button from '../button';
import { RiLockPasswordFill, GiSittingDog } from "react-icons/ri";
import { LiaAccusoft } from "react-icons/lia";

function Navbar (){

    const [click, setClick]= useState(false);
    const [button, setButton]= useState(false);

    const handleClick = ()=> setClick(!click);
    const closeMobileMenu= ()=>setClick(false);
    const showButton = () => {
        if (window.innerWidth <= 960){
            setButton(false);
        }else{
            setButton(true);
        }

    };
    useEffect(() =>{
        showButton();
    }, []);
    
    window.addEventListener('resize',showButton);

        return(
            <div className='navbar'>
                <div className='navbar__container'>
                
                    <h2 className='navbar__logo'> <LiaAccusoft />  Cc</h2>
                      
                        {/* <div className='menu__icon'>
                            <i className={click ? 'fas fa-times' : 'fas fa-bars'}/>
                        </div>
                 */}
                    <ul className= {click?'nav__menu active':'nav__menu'}>
                        <li className='nav__item'>
                            <Link to="/" className='nav__links' onClick={closeMobileMenu}>Home</Link>
                        </li>
                        <li className='nav__item'>
                            <Link to="/Classes" className='nav__links' onClick={closeMobileMenu}>Classes</Link>
                        </li>
                        <li className='nav__item'>
                            <Link to="/blog" className='nav__links' onClick={closeMobileMenu}>Blog</Link>
                        </li>
                        <li className='nav__item'>
                            <Link to="/contact" className='nav__links' onClick={closeMobileMenu}>Contact</Link>
                        </li>
                    </ul>

                        <div className='menu__icon' onClick={handleClick}>
                            <i className={click ? 'fas fa-times' : 'fas fa-bars'}/>
                        </div>

                        {button && <Button buttonStyle= 'btn--outline'>Login/Signup</Button>}
                </div>
            </div>

    
        // <section className="navbar">
        //     <ul className='navbar-item'>
        //         <li className='nav-item'>
        //             <Link to="/" className='nav-links' onClick={closeMobileMenu}>Home</Link>
        //         </li>
        //         <li className='nav-item'>
        //             <Link to="/services" className='nav-links' onClick={closeMobileMenu}>Services</Link>
        //         </li>
        //         <li className='nav-item'>
        //             <Link to="/blog" className='nav-links' onClick={closeMobileMenu}>Blog</Link>
        //         </li>
        //         <li className='nav-item'>
        //             <Link to="/contact" className='nav-links' onClick={closeMobileMenu}>Contact</Link>
        //         </li>
        //     </ul>
        // </section>

         );

}
export default Navbar;