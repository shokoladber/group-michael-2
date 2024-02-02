import React from "react";
//  import Navbar from "../navbar";
import './Header.css';

function Header () {

    return(
        <sectiion className="header">
            <section className="header-top">
            <section className="header-top__logo">
                <a href="/" className="header-logo">Canine Coach</a>
            </section>
            <section className="header-top__navbar">
            <i class="fa-solid fa-dog"></i>
                <section className="header-top__navigation">
            {/* <Navbar/>  */}
            </section>
            <hr className="header-top__seperator"/>
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