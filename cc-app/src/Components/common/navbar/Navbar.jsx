import React from "react"; 
import './Navbar.css';
import { FaHouseChimneyWindow } from "react-icons/fa6";

function Navbar (){

    return(
        <section className="navbar">
            <a href="/" className="navbar-item">Home</a>
            <a href="/blog" className="navbar-item">Blog</a>
            <a href="/contact" className="navbar-item">Contact Us</a>
        </section>

    )

}
export default Navbar;