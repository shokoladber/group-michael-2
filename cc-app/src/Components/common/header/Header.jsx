import React from "react";
// import logo from 'K9 4.jpg'
//  import Navbar from "../navbar";
import './Header.css';

function Header () {

    return(
        <sectiion className="header">

            
            <section className="header-top">
            <section className="header-top__logo">
                <a href="/" className="header-logo">Canine  Coach</a>
            </section>
            <section className="header-top__navbar">
            {/* <img src= {logo} alt=""/> */}
            
            <section className="header-top__navigation">
            {/* <Navbar/>  */}
            </section>
        
            </section>
            </section>
            <section className="header-bottom">
            <section className="header-bottom__phone">
                {/* 3145554444 */}
            </section>
            <section className="header-bottom__email">
                {/* caninecoach314@gmail.com */}
            </section>
            </section>
        </sectiion>

    )
}
export default Header;