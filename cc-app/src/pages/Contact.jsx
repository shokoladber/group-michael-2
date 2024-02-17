import React from 'react';
// import { Header, Navbar } from '../Components/common'
import Logo from '../Components/Assets/bg2.jpg';
import Topbar from '../Components/common/topbar/topbarIndex';

import ContactUs from '../Components/common/contact2';
import Header from "../Components/common/header/headerIndex";


function Contact() {
    return (
        <div>
            <Header />

            <h2 >Contact Page</h2>
            {/* <img src={Logo} alt='' />  */}
            <ContactUs />
        </div>

    )
}
export default Contact;
