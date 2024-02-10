import React from 'react'
 import vid1 from '../Components/Assets/vid1.mp4';

function Home() {
  return (
    <div className='home'>
        <video src={vid1} autoPlay muted loop></video>
          <div className='overlay'></div>
          <div className='content'>
            <h1> WELCOME TO CANINE COACH</h1>
            <p>Here to guide your training needs</p>
            <p>Your training needs in one place</p>
            <button>Login</button>

          </div>





           {/* <h2>Home Page</h2>
         <div className='App>'>
            <video autoPlay loop muted
              style={{
                position: 'static',
                width: '100%',
                left: '90%',
                top: '90%',
                height:'100%',
                objectFit:'fill',  
                transform:'translate((-50%, -50%)',
                zIndex:'-1'
              
              }} 
            >
              <source src={vid1} type='video/mp4'/>
            </video>

         </div>
      */}
    </div>
  )
}
export default Home;