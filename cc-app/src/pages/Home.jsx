import React from 'react';
import vid1 from '../Components/Assets/vid1.mp4';
import { Header, Navbar } from '../Components/common';
import '../App.css';

function Home() {
    const handleLoginRegister = () => {
        window.location.href = `${process.env.REACT_APP_BACKEND_URL}/oauth2/authorization/google`; // Update with your actual login URL
    };

    return (
        <div>
            <Navbar />
            <Header />
            <div className='App'>
                <video autoPlay loop muted
                       style={{
                           position: 'absolute',
                           width: '100%',
                           left: '50%',
                           top: '50%',
                           height: '100%',
                           objectFit: 'cover',
                           transform: 'translate(-50%, -50%)',
                           zIndex: '-1'
                       }}
                >
                    <source src={vid1} type='video/mp4' />
                </video>
                <button className="login-register-btn" onClick={handleLoginRegister}>Login/Register</button>
            </div>
            <h2>Home Page</h2>
        </div>
    );
}
export default Home;
