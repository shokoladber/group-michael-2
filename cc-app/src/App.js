import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './pages/Home';
import Blog from './pages/Blog';
import Contact from './pages/Contact';
import Classes from './pages/Classes';
import NoPage from './pages/NoPage';
import {LoginSignup, Navbar  } from './Components/common';
// import BlogPost from './Components/common/blogpost/BlogPost';
import Topbar from './Components/common/topbar/Topbar';
// import PetProfilePage from './Components/common/petProfilePage';


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
