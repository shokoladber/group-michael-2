import React, { useEffect, useState } from 'react';
import './Navbar.css';
import {Link} from 'react-router-dom'
import Button from '../button';
import { RiLockPasswordFill, GiSittingDog } from "react-icons/ri";
import { LiaAccusoft } from "react-icons/lia";
import Dropdown from '../dropdown';

function Navbar (){

    const [click, setClick]= useState(false);
    const [button, setButton]= useState(false);
    const [dropdown, setDropdown] = useState(false);

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
                                
                    <h2 className='navbar__logo'> <LiaAccusoft />  CC</h2>
                      
                            <div className='menu__icon'> 
                                <i className={click ? 'fas fa-times' : 'fas fa-bars'}/>
                            </div>
                            <div className='menu'>
                                <span></span>
                                <span></span>
                                <span></span>

                            </div>
                 
                    <ul className= {click?'nav__menu active':'nav__menu'}>
                        <li className='nav__item'>
                            <Link to="/" className='nav__links' onClick={closeMobileMenu}>Home</Link>
                        </li>
                        <li className='nav__item'>
                            <Link to="/Classes" className='nav__links' onClick={closeMobileMenu}>Classes<i className='fas fa-caret-down'/></Link>
                                 {dropdown && <Dropdown/>}
                        </li>
                        <li className='nav__item'>
                            <Link to="/blog" className='nav__links' onClick={closeMobileMenu}>Blog</Link>
                        </li>
                        <li className='nav__item'>
                            <Link to="/contact" className='nav__links' onClick={closeMobileMenu}>Contact</Link> 
                        </li>
                    </ul>

                        {button && <Button buttonStyle= 'btn--primary'>Login</Button>}

                        {/* {button && <Button buttonStyle= 'btn--outline'>SignUp</Button>} */}
                
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