import React, { useState } from 'react'
import { RiLockPasswordFill } from "react-icons/ri";
import { MdEmail } from "react-icons/md";
import './LoginSignup.css'
import { FaUser } from "react-icons/fa";


const LoginSignup = () => {
  const [action,setAction] = useState("Login");
  return (
    <div className="container">
        <div className="header">
            <div className= "text" >{action}</div>
            <div className="underline"></div>
        </div>
        
        <div className="inputs">
          {action==="Login"?<div></div>:<div className="input">
            
            <input type="text" placeholder="Name"/>
            <FaUser />
         </div> }
          
         <div className="input">
            <input type="email" placeholder="Email"/>
            <MdEmail />
         </div>    
          <div className="input">
            <input type="password" placeholder="Password"/>
            <RiLockPasswordFill />
          </div>    
        </div>
        {action==="Sign Up"?<div></div>:<div className="forgot-password">Lost Password?<span> Click Here!</span></div>}
        <div className="submit-container">
            <div className={action==="Login"?"submit gray":"submit"} onClick={()=>{setAction("Sign Up")}}>Sign Up</div>
            <div className={action==="Sign Up"?"submit gray":"submit"} onClick={()=>{setAction("Login")}}>Login</div>
        </div>
    </div>
  )
}

export default LoginSignup;