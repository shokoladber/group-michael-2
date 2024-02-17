import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import {Home, Blog, Classes, Login, NoPage} from './pages';
import {Header, Navbar, LoginSignup, BlogPost, PetProfilePage, Button, Footer, FileUpload } from './Components/common/commonIndex';
import { Dropdown } from 'react-bootstrap';
import Contact from './Components/common/contact2/ContactUs';


function App() {
  return (
         
      <BrowserRouter>
        <>
            <Navbar/>
            {/* <FileUpload /> */}
            <Header />
            {/* <Dropdown/> */}
            {/* <PetProfilePage/> */}
            <Routes>
          
              <Route index element= {<Home/>} />
              <Route path="/home" element={<Home/>}/>
              <Route path="/blog" element={<Blog/>}/>  
              <Route path="/petprofile" element={<PetProfilePage/>}/>
              <Route path="/contact" element={<Contact/>}/>       
              <Route path="/classes" component={<Classes/>}/>
              <Route path="/Login" element={<LoginSignup/>}/>  
              <Route path="/SIGN UP" element={<LoginSignup/>}/>  
              <Route path="*" element={<NoPage/>}/>       
              
            </Routes>
        </>
      </BrowserRouter>
    
  );
}

export default App;
