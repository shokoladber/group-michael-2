import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Home, Blog, Classes, Login, NoPage} from './pages';
import { Header, Navbar, LoginSignup, BlogPost, PetProfilePage, Button, Footer, FileUpload, ContactUs } from './Components/common';
import { Dropdown } from 'react-bootstrap';
import Contact from './Components/common/contact2/ContactUs';
import BuyClasses from './Components/common/buy/BuyClasses'

function App() {
  return (
    <>
      
      <BrowserRouter>
        {/* <Topbar /> */}
        <Navbar/>
  {/* / <PetProfilePage /> */}
        
        <Routes>
      
          <Route index element= {<Home/>} />
          <Route path="/home" element={<Home/>}/>
          <Route path="/blog" element={<Blog/>}/>       
          <Route path="/contact" element={<Contact/>}/>       
          <Route path="/classes" element={<Classes/>}/>
          <Route path="/Login" element={<LoginSignup/>}/>  
          <Route path="/SIGN UP" element={<LoginSignup/>}/>  

          <Route path="*" element={<NoPage/>}/>       
          
        </Routes>
       
      </BrowserRouter>


    </>
  );
}

export default App;

