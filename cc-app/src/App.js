import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Home, Blog, Courses, Login, NoPage} from './pages';
import { Header, Navbar, LoginSignup, BlogPost, PetProfilePage, Button, Footer, FileUpload, ContactUs } from './Components/common';
import Contact from './Components/common/contact2/ContactUs';
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
          <Route path="/petprofile" element={<PetProfilePage />} />
          <Route path="/contact" element={<Contact />} />
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

