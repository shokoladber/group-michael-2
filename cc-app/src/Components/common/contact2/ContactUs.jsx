import React from 'react'
 import {Container, Row, Col} from "react-bootstrap";
 import {contactConfig} from './Content_Option'


function Contact() {
  return (
    <Container>
        <Row className='mb-5 mt-3'>
            <Col lg='8'>
                <h1 className='display-4 mb-4'>Contact Us</h1>
            </Col>
        </Row>

        <Row className='sec_sp .text-center'>
            <Col lg='5' className='mb-5'>
                <h3 className='color_sec py-4'>Get in Contact</h3>
                <address>
                    <strong>Email : caninecoach314@gmail.com </strong>
                    <br/>
                    <br />
                    <p>
                        <strong>Phone : 314 xxx xxxx</strong>
                    </p>

                </address>
                <p>{contactConfig.description}</p>
            </Col>
            <Col lg='9' className='d-flex align-items-center'>
                <form className='contact_form w-100'>
                    <Row>
                        <Col lg='6' className='form-group'>
                            <input
                                className='form-control'
                                id='name'
                                placeholder='Name'
                                type='text'
                                />
                        
                        </Col>
                        <Col lg='6' className='form-group'>
                            <input
                                className='form-control rounded-0'
                                id='email'
                                placeholder='Email'
                                type='email'
                                />
                        
                        </Col>
                        
                    </Row>
                    <textarea 
                    className='form-control rounded-0' id='message'
                                name='message'
                                placeholder='Message'
                                rows='5'
                    ></textarea>   
                    <br />
                    <Row>
                    <Col lg='12' className='form-group'>
                        <button className='btn ac_btn' type='submit'>Send</button>
                    </Col>   
                    </Row>
                </form>
            </Col>
        </Row>
    </Container>
  )
}

export default Contact