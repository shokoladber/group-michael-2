import React from "react";
import './Header.css';
import pix1 from '../../Assets/px5.jpg';
import pix2 from '../../Assets/px6.jpg';
import PetProfilePage from "../profile/PetProfilePage";


function Header() {
  return (
    <div className="header section__padding" id='home'>
      <div className="header-content">
        <h1 className="header-title">Unleash Your Dog's Potential with Canine Coach</h1>
        <p className="header-subtitle">Transforming Paws into Well-Behaved Champions!</p>
        
        <div className="header-input">
           {/* <PetProfilePage/>  */}
          {/* <input type="email" placeholder="Enter Your Email Address" /> */}
          <button>Sign Up</button>
        </div>

        <div className="header-trainers">
          <img src={pix2} alt="Trusted Trainers" className="header-trainer-img" />
          <p>Trusted Trainers</p>
        </div>
      </div>

      <div className="header-image">
        <img src={pix1} alt='' className="header-img" />
      </div>
    </div>
  );
}

export default Header;


// function Header () {

//     return(

//         <div className="header section__padding" id='home'>
//             <div className="header-content">
//                 <h1 className="gradient__text"> Unleash Your Dog's Potential with Canine Coach: Where Every Tail Wags with Success! </h1>
//                 <p>Transforming Paws into Well-Behaved Champions! </p>
                
//                 <div className="header-content__input">
//                     <input type="email" placeholder="Your Email Address" />
//                     <button type="button">Get Started</button>
//                 </div>

//                 <div className="header-content__people">
//                     <img src={pix1} alt="pixs" />
//                     <p>Trusted Trainers</p>
//                 </div>
//             </div>
//                 <div className="header-image">
//                     <img src={pix2} alt='' />
//                 </div>     
//         </div>
//         // <sectiion className="header">

            
//         //     <section className="header-top">
//         //     <section className="header-top__logo">
//         //         <a href="/" className="header-logo">Canine Coach</a>
//         //     </section>
//         //     <section className="header-top__navbar">
//         //     {/* <img src= {logo} alt=""/> */}
            
//         //     <section className="header-top__navigation">
//         //     {/* <Navbar/>  */}
//         //     </section>
        
//         //     </section>
//         //     </section>
//         //     <section className="header-bottom">
//         //     <section className="header-bottom__phone">
//         //         {/* 3145554444 */}
//         //     </section>
//         //     <section className="header-bottom__email">
//         //         {/* caninecoach314@gmail.com */}
//         //     </section>
//         //     </section>
//         // </sectiion>

//     )
// }
// export default Header;