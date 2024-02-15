import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Blog from './pages/Blog';
import Contact from './pages/Contact';
import Classes from './pages/Classes';
import NoPage from './pages/NoPage';
import { LoginSignup, Navbar } from './Components/common';
import OAuth2RedirectHandler from './Components/auth/OAuth2RedirectHandler'; // Make sure to create this component
import Topbar from './Components/common/topbar/Topbar';

function App() {
    return (
        <>
            <BrowserRouter>
                <Navbar />

                <Routes>
                    <Route index element={<Home />} />
                    <Route path="/home" element={<Home />} />
                    <Route path="/blog" element={<Blog />} />
                    <Route path="/contact" element={<Contact />} />
                    <Route path="/classes" element={<Classes />} />
                    <Route path="/login" element={<LoginSignup action="Login" />} />
                    <Route path="/signup" element={<LoginSignup action="Sign Up" />} />
                    <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
                    <Route path="*" element={<NoPage />} />
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;
