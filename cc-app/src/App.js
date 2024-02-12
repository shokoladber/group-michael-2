import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route, Switch } from 'react-router-dom'
import Home from './pages/Home';
import Blog from './pages/Blog';
import Contact from './pages/Contact';
import Services from './pages/Services';
import NoPage from './pages/NoPage';
import { LoginSignup, Navbar } from './Components/common';



function App() {
  return (
    <>
      
      <BrowserRouter>
        <Routes>
         
          <Route index element= {<Home/>} />
          <Route path="/home" element={<Home/>}/>
          <Route path="/blog" element={<Blog/>}/>       
          <Route path="/contact" element={<Contact/>}/>       
          <Route path="/services" element={<Services/>}/>
          <Route path="/LoginSignup" element={<LoginSignup/>}/>  

          <Route path="*" element={<NoPage/>}/>       
        
       
        </Routes>
        {/* <LoginSignup/> */}
      </BrowserRouter>


    </>
  );
}

export default App;