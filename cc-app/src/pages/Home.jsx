import React from 'react'
 import vid1 from '../Components/Assets/vid1.mp4';

function Home() {
  return (
    <div>
        
         <div className='App>'>
            <video autoPlay loop muted
              style={{
                position: 'static',
                width: '100%',
                left: '50%',
                top: '50%',
                height:'100%',
                objectFit:'fill',  
                transform:'translate((-50%, -50%)',
                zIndex:'-1'
              
              }} 
            >
              <source src={vid1} type='video/mp4'/>
            </video>

         </div>
        <h2>Home Page</h2>
    </div>
  )
}
export default Home;