import React from 'react';
import './App.css';
// import { BrowserRouter as Router, Route } from 'react-router-dom';

// import Button from './Components/common/button';
import Navbar from './Components/common/navbar';
import { Header, LoginSignup } from './Components/common';



function App() {
  return (
    <>
      {/* <Router> */}
      <Navbar/>
      <Header/>
      <LoginSignup/>
  
        {/* <Switch> */}
          {/* <Route path='/' exact /> */}
        {/* </Switch> */}
      {/* </Router> */}
    </>
  );
}

export default App;
