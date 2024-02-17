import { RiLockPasswordFill, GiSittingDog , RiMenu3Line, RiCloseLine} from "react-icons/ri";
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
            <Link to="/classes" className='nav__links' onClick={() => setDropdownOpen(false)}>Classes <i className='fas fa-caret-down' /></Link>
            {dropdownOpen && <Dropdown />} {/* Render the dropdown if dropdownOpen is true */}
          </li>
          <li className='nav__item'>
            <Link to="/blog" className='nav__links' onClick={() => setDropdownOpen(false)}>Blog</Link>
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










// function Navbar (){

//     const [click, setClick]= useState(false);
//     const [button, setButton]= useState(false);
//     const [dropdown, setDropdown] = useState(false);

//     const handleClick = ()=> setClick(!click);
//     const closeMobileMenu= ()=>setClick(false);

//     const showButton = () => {
//         if (window.innerWidth <= 960){
//             setButton(false);
//         }else{
//             setButton(true);
//         }

//     };
//     useEffect(() =>{
//         showButton();
//     }, []);
    
//     window.addEventListener('resize',showButton);
//     return(
    
//             <nav className='navbar'>
//                 <div className='navbar-container'>
                                
//                     <Link to='/' className='navbar__logo' onClick={closeMobileMenu}> <LiaAccusoft />  CC</Link>
                      
//                             <div className='menu__icon' onClick={handleClick}> 
//                                 <i className={click ? 'fas fa-times' : 'fas fa-bars'}/>
//                             </div>
                             
//                     <ul className= {click?'nav__menu active':'nav__menu'}>
//                         <li className='nav__item'>
//                             <Link to="/" className='nav__links' onClick={closeMobileMenu}>Home</Link>
//                         </li>
//                         <li className='nav__item'>
//                             <Link to="/Classes" className='nav__links' onClick={closeMobileMenu}>Classes<i className='fas fa-caret-down' /></Link>
//                              {dropdown && <Dropdown/>}
//                         </li>
//                         <li className='nav__item'>
//                             <Link to="/blog" className='nav__links' onClick={closeMobileMenu}>Blog</Link>
//                         </li>
//                         <li className='nav__item'>
//                             <Link to="/contact" className='nav__links' onClick={closeMobileMenu}>Contact</Link> 
//                         </li>
//                         <li className='nav__item'>
//                             <Link to="/Login" className='nav__links' onClick={closeMobileMenu}>Login</Link> 
//                         </li>
//                         {/* <li className='nav__item'>
//                             <Link to="/Sign Up" className='nav__links' onClick={closeMobileMenu}>Login</Link> 
//                         </li> */}
//                     </ul>

//                         {/* {button && <Button buttonStyle= 'btn--primary'>Login</Button>} */}

//                         {/* {button && <Button buttonStyle= 'btn--outline'>SignUp</Button>} */}
//                 </div>
//             </nav>

    

//     );


// }
// export default Navbar;