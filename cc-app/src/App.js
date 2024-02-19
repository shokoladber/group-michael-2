import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Home, Blog, Courses,NoPage, PetProfile} from './pages';
import { Navbar, LoginSignup, ContactUs } from './Components/common';
import BuyCourses from './Components/common/buy/BuyCourses'

function App() {
  return (
    <Router>
      <>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/blog" element={<Blog />} />
          <Route path="/petProfile" element={<PetProfile />} />
          <Route path="/contact" element={<ContactUs />} />
          <Route path="/courses" element={<Courses />} />
          <Route path="/purchase" element={<BuyCourses />} />
          <Route path="/login" element={<LoginSignup />} />
          <Route path="/SIGN UP" element={<LoginSignup />} />
          <Route path="*" element={<NoPage />} />
        </Routes>
      </>
    </Router>
  );
}

export default App;

