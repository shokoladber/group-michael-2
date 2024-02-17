import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Blog from './pages/Blog';
import Contact from './pages/Contact';
import Classes from './pages/Classes';
import NoPage from './pages/NoPage';
import Login from './pages/Login';
import Services from './pages/Services';
import UserLogin from './Components/auth/UserLogin';
import UserSignup from './Components/auth/UserSignup';
import UserRoleSelection from './Components/auth/UserRoleSelection';
import OAuth2RedirectHandler from './Components/auth/OAuth2RedirectHandler';
import Topbar from './Components/common/topbar/Topbar';
import Navbar from './Components/common/navbar/Navbar';
import Header from './Components/common/header/Header';

function App() {
    return (
        <BrowserRouter>
            <Topbar />
            <Navbar />
            <Header /> {/* Make sure Header is rendered if needed */}
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/blog" element={<Blog />} />
                <Route path="/contact" element={<Contact />} />
                <Route path="/classes" element={<Classes />} />
                <Route path="/login" element={<Login />} />
                <Route path="/services" element={<Services />} />
                <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
                <Route path="/signup" element={<UserSignup />} />
                <Route path="/select-role" element={<UserRoleSelection />} />
                <Route path="*" element={<NoPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;