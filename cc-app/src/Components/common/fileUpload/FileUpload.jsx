import React, {useState} from 'react'
import './FileUpload.css'

function FileUpload() {
const [selectedImage, setSelectedImage]= useState(null);


  return (
    <div className='profile'>
        <h1> Create a Pet Profile</h1>
        
        {selectedImage &&(
            <div>
                <img
                    alt="not found"
                    width={"250px"}
                    src={URL.createObjectURL(selectedImage)}
                />
                <br/>
                <button onClick={() => selectedImage(null)}>Remove</button>
            </div>    
        )}
        <br/>
        <br/>
        
            <input  
                type='file'
                name='myImage'
                onChange={(event)=> {
                    console.log(event.target.files[0]);
                    setSelectedImage(event.target.files[0]);
                }}
                />
    </div>
  );
};

export default FileUpload;