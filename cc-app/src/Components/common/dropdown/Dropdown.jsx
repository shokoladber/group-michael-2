import React from 'react';
import {Link} from 'react-router-dom';
import './Dropdown.css'; 

function Dropdown() {
  return (
    <div className='dropdown'>
      <ul className='dropdown-menu'>
        <li className='dropdown-item'>
          <Link to='/class-curricula' className='dropdown-link'>Week 1</Link>
        </li>
        <li className='dropdown-item'>
          <Link to='/class-homework' className='dropdown-link'>Week 2</Link>
        </li>
        <li className='dropdown-item'>
          <Link to='/checkout' className='dropdown-link'>Week 3</Link>
        </li>
        <li className='dropdown-item'>
          <Link to='/class-curricula' className='dropdown-link'>Week 4</Link>
        </li>
        <li className='dropdown-item'>
          <Link to='/class-homework' className='dropdown-link'>Week 5</Link>
        </li>
        <li className='dropdown-item'>
          <Link to='/checkout' className='dropdown-link'>Week 6</Link>
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