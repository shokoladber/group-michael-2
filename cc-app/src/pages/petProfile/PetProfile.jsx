import React from 'react';
import './PetProfile.css';
import pix4 from '../../Components/Assets/dogpk3.jpg'
import { FileUpload, PetProfilePage } from '../../Components/common';


function PetProfile() {
  return (
    <div>
        <div className="petProfile-image">
        {/* <img src={pix4} alt='' className="petProfile-img" /> */}
      </div>
        <FileUpload/>
        {/* <PetProfilePage/> */}

    </div>
  )
}

export default PetProfile;