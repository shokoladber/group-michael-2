import React from 'react';
import { contactConfig } from './Content_Option';
import './ContactUs.css'; 

function Contact() {
  return (
    <div className='container'>
      <div className='row mb-5 mt-3'>
        <div className='col-lg-8'>
          <h1 className='display-4 mb-4'>Contact Us</h1>
        </div>
      </div>

      <div className='row sec_sp text-center'>
        <div className='col-lg-5 mb-5'>
          <h3 className='color_sec py-4'>Get in Contact</h3>
          <address>
            <strong>Email : caninecoach314@gmail.com </strong>
            <br />
            <br />
            <p>
              <strong>Phone : 314 xxx xxxx</strong>
            </p>
          </address>
          <p>{contactConfig.description}</p>
        </div>
        <div className='col-lg-7'>
          <div className='contact-form-container'>
            <form className='contact-form'>
              <div className='row'>
                <div className='col-lg-6 form-group'>
                  <input
                    className='form-control'
                    id='name'
                    placeholder='Name'
                    type='text'
                  />
                </div>
                <div className='col-lg-6 form-group'>
                  <input
                    className='form-control rounded-0'
                    id='email'
                    placeholder='Email'
                    type='email'
                  />
                </div>
              </div>
              <textarea
                className='form-control rounded-0'
                id='message'
                name='message'
                placeholder='Message'
                rows='5'
              ></textarea>
              <br />
              <div className='form-group'>
                <button className='btn ac_btn' type='submit'>
                  Send
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Contact;

