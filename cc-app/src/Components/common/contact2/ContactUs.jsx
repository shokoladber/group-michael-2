import React, {useState} from 'react';
import { contactConfig } from './Content_Option';
import './ContactUs.css'; 

function Contact() {
  const [formData, setFormData]=useState({
    name:'',
    email:'',
    message:'',
    
  });

  const handleChange = (e)=> {
      const {name, value} = e.target;
      setFormData(prevState => ({
        ...prevState,
        [name]: value
      }));
  };
  const handleSubmit =(e)=> {
    e.preventDefault();

    console.log(formData);
    setFormData({
      name:'',
      email:'',
      message:'',
    });
  };
  
  return (
    <form onSubmit={handleSubmit} className='contact-form' action='https://formspree.io/f/xpzvdbyp' method='POST' data-email='caninecoach314@gmail.com'>
      <div className='form-group'>
         <h1>Contact Us</h1>
         <br/>

          <h2><strong> caninecoach314@gmail.com </strong></h2>
          <br/>
          <p>{contactConfig.description}</p>
          <br/>

        
          <label htmlFor='name'>Name:</label>
          <input type='text' id='name' name='name' value={formData.name} onChange={handleChange}/>
      </div>
      <div className='form-group'>
          <label htmlFor='email'>Email:</label>
          <input type='email' id='email' name='email' value={formData.email} onChange={handleChange}/>
      </div>
      <div className='form-group'>
          <label htmlFor='message'>Message:</label>
          <textarea id='message' name='message' value={formData.message} onChange={handleChange}/>
      </div>
        <button type='submit'>Submit</button>
    
    </form>
  );
};

export default Contact;

