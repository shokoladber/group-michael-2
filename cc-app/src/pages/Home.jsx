import React from 'react'
 import vid1 from '../Components/Assets/vid1.mp4';
import { Link } from 'react-router-dom';



function Home() {
  return (
    <div className='home'>
        <video src={vid1} autoPlay muted loop></video>
          <div className='overlay'></div>
          <div className='content'>
            <h1> WELCOME TO CANINE COACH</h1>
            <p>Guiding your training needs</p>
            {/* <p>Your training needs in one place</p> */}
           <Link to='/LoginSignup'><button>SIGN UP</button></Link> 

          </div>    
    </div>
  )
}
export default Home;