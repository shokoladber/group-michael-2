import React from 'react';
import Navbar from '../Components/common/navbar/Navbar'; // Correct the path if it's incorrect
import Header from '../Components/common/header/Header'; // Correct the path if it's incorrect

const Services = () => {
    return (
        <div>
            <Navbar />
            <Header />
            <h1>Services</h1>
            <p>This is the Services page where we offer our services.</p>
            {/* You can add more content related to your services here */}
        </div>
    );
};

export default Services;
