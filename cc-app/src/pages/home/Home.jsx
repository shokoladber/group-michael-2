import React from 'react'
import './Home.css';
import vid1 from '../../Components/Assets/vid1.mp4';
import { Link } from 'react-router-dom';
import { Header } from '../../Components/common';



function Home() {
  return (
        <div className='home'>
          <Header/>


        </div>
    // <div className='home'>
    //   <div className='video-container'>
    //     <video autoPlay muted loop>
    //       {/* <source src={vid1} type='video/mp4'/> */}
    //     </video>
          
    //   </div>
    //     <div className='overlay'></div>
         
    //       <div className='content'>

    //         <h1> WELCOME TO CANINE COACH</h1>

    //         <p>Guiding your training needs</p>
    //         {/* <p>Your training needs in one place</p> */}
    //        {/* <Link to='/LoginSignup'><button>SIGN UP</button></Link>  */}

    //       </div>    
    // </div>
  )
}
export default Home;