import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Home, Blog, Courses, NoPage } from './pages';
import { Navbar } from './Components/common';
import { UserLogin, UserSignup, UserRoleSelection, OAuth2RedirectHandler, VerifyEmail } from './Components/auth/authIndex';
import Contact from './Components/common/contact2/ContactUs';
import BuyClasses from './Components/common/buy/BuyCourses';
import { PetProfilePage, TrainerProfilePage } from './Components/common/profile/profileIndex';

function App() {
  return (
      <Router>
        <>
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/home" element={<Home />} />
            <Route path="/blog" element={<Blog />} />
            <Route path="/petprofile" element={<PetProfilePage />} />
            <Route path="/contact" element={<Contact />} />
            // Update the component for the '/classes' route to 'Courses'
            <Route path="/classes" element={<Courses />} />
            <Route path="/purchase" element={<BuyClasses />} />
            <Route path="/login" element={<UserLogin />} />
            <Route path="/signup" element={<UserSignup />} />
            <Route path="/verify-email" element={<VerifyEmail />} />
            <Route path="/select-role" element={<UserRoleSelection />} />
            <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
            <Route path="/pet-profiles" element={<PetProfilePage />} />
            <Route path="/trainer-profiles" element={<TrainerProfilePage />} />
            <Route path="*" element={<NoPage />} />
          </Routes>
        </>
      </Router>
  );
}

export default App;