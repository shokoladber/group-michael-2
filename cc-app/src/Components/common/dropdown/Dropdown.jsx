import React from 'react';
import {Link} from 'react-router-dom';
import './Dropdown.css'; 

function Dropdown() {
  return (
    <div className='dropdown'>
      <ul className='dropdown-menu'>
        <li className='dropdown-item'>
          <Link to='/class-curricula' className='dropdown-link'>Class Curricula</Link>
        </li>
        <li className='dropdown-item'>
          <Link to='/class-homework' className='dropdown-link'>Class Homework</Link>
        </li>
        <li className='dropdown-item'>
          <Link to='/checkout' className='dropdown-link'>Check Out</Link>
        </li>
      </ul>
    </div>
  );
}

export default Dropdown;



















// import React,{useState} from "react";
// import './Dropdown.css';
// import {Link} from 'react-router-dom';
// import { NavDropdownItems } from "./NavDropdownItems";

// function Dropdown() {

// const [click, setClick]= useState(false);
// const handleClick= ()=> setClick(!click);

//   return (
//     <>
//         <ul
//             onClick={handleClick}
//             className={click ? 'dropdown-menu clicked': 'dropdown-menu'}
//         >
//             {NavDropdownItems.map((item, index) =>{
//                 return(
//                     <li key={index}>
//                         <Link
//                             className={item.cName}
//                             to={item.path}
//                             onClick={()=> setClick(false)}
//                         >
//                             {item.title}
//                         </Link>
//                     </li>
//                 );
                        
//             })}
                            
            
//             </ul>    
//        </>
//   );
// }

// export default Dropdown;