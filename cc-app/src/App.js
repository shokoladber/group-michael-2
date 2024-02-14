import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Blog from './pages/Blog';
import Contact from './pages/Contact';
import Services from './pages/Services';
import NoPage from './pages/NoPage';
import PrivatePage from './components/private/PrivatePage.jsx';
import OAuth2RedirectHandler from './components/auth/OAuth2RedirectHandler.jsx';
import UserRoleSelection from './components/auth/UserRoleSelection.jsx';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/blog" element={<Blog />} />
                <Route path="/contact" element={<Contact />} />
                <Route path="/services" element={<Services />} />
                <Route path="/private" element={<PrivatePage />} />
                <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
                <Route path="/select-role" element={<UserRoleSelection />} />
                <Route path="*" element={<NoPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
