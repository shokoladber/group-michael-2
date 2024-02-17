import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Home, Blog, Classes, NoPage } from './pages';
import { Header, Navbar, BlogPost, PetProfilePage, Button, Footer, FileUpload, ContactUs } from './Components/common';
import { UserLogin, UserSignup, UserRoleSelection, OAuth2RedirectHandler } from './Components/common/auth/authIndex';
import Contact from './Components/common/contact2/ContactUs';
import BuyClasses from './Components/common/buy/BuyClasses';
import { Dropdown } from 'react-bootstrap';

function App() {
  return (
      <>
        <Router>
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/home" element={<Home />} />
            <Route path="/blog" element={<Blog />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/classes" element={<Classes />} />
            <Route path="/login" element={<UserLogin />} />
            <Route path="/signup" element={<UserSignup />} />
            <Route path="/select-role" element={<UserRoleSelection />} />
            <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
            <Route path="*" element={<NoPage />} />
          </Routes>
        </Router>
      </>
  );
}

export default App;
