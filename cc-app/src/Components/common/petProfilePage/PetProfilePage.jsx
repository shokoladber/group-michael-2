// import React from 'react';
// import './PetProfilePage.css';

// const PetProfilePage = ({ pet }) => {
    
//   return (
//     <div className="pet-profile">
//       <h1>Pet Profile</h1>
//       <div className="pet-details">
//         <div>
//           <strong>Name:</strong> {pet.name}
//         </div>
//         <div>
//           <strong>Age:</strong> {pet.age}
//         </div>
//         <div>
//           <strong>Breed:</strong> {pet.breed}
//         </div>
//         <div>
//           <strong>Description:</strong> {pet.description}
//         </div>
//         <div>
//           <strong>Owner:</strong> {pet.owner}
//         </div>
//         <div>
//           <strong>Contact:</strong> {pet.contact}
//         </div>
//       </div>
//       <div className="pet-image">
//         <img src={pet.imageUrl} alt={pet.name} />
//       </div>
//     </div>
//   );
// };

// export default PetProfilePage;





// // import React, {useState, useEffect} from "react";
// // import axios from 'axios';


// // function PetProfilePage() {

// //   const [pet, setPet]= useState(null);
// //   const[isEditing, setIsEditing] = useState(false);
// //   const [formData, setFormData]= useState({
// //       name: '',
// //       age: '',
// //       breed: '',
// //       description: '',
      
// //   })

// //   useEffect(() =>{
// //     fetchPetData();
// //   }, []);

// //   const fetchPetData= async () =>{
// //     try {
// //       const response = await axios.get('/api/pet/123');
// //       setPet(response.data);
// //     } catch (error) {
// //       console.error('Error fetching pet data:', error);
// //     }

// //   };

// //   const handleEditSubmit = async (e) => {
// //     e.preventDefault();
// //     try {
// //       await axios.put('/api/pet/123', formData);
// //       setPet(formData);
// //       setIsEditing(false);
// //     } catch (error) {
// //       console.error('Error updating pet data:', error);
// //     }
// //   };

// //   const handleChange = (e)=> {
// //     setFormData({
// //       ...formData,
// //       [e.target.name]: e.target.value
// //     });
// //   };

// //   return (
// //     <div style={styles.container} >
// //       {pet ? (
// //         <div style={styles.profile}>
// //           <h1>{pet.name}</h1>
// //           <p>Age: {pet.age}</p>
// //           <p>Breed: {pet.breed}</p>
// //           <p>Description: {pet.description}</p>
// //           {isEditing ? (
// //             <form onSubmit={handleEditSubmit} style={styles.form}>
// //               <input
// //                 type="text"
// //                 name="age"
// //                 placeholder="Age"
// //                 value={formData.age}
// //                 onChange={handleChange}
// //                 style={styles.input}
// //               />
// //               <input
// //                 type="text"
// //                 name="breed"
// //                 placeholder="Breed"
// //                 value={formData.breed}
// //                 onChange={handleChange}
// //                 style={styles.input}
// //               />
// //               <input
// //                 type="text"
// //                 name="description"
// //                 placeholder="Description"
// //                 value={formData.description}
// //                 onChange={handleChange}
// //                 style={styles.input}
// //               />
// //               <button type="submit" style={styles.button}>Save</button>
// //               <button onClick={ () => setIsEditing(false)} style={styles.button}>Cancel</button>
// //             </form>
// //           ) :(
// //             <button onClick={ () => setIsEditing(true)} style={styles.button}>Edit</button>
// //           )}
// //     </div>    
// //       ) :(
// //             <p>Load...</p>

// //           )}

// //     </div>
// //   )
// // };

// // const styles = {

// //   container: {
// //     maxWidth: '600px',
// //     margin: 'auto',
// //     padding:'20px',

// //   },
// //   profile:{
// //     backgroundColor:'#f4f4f4',
// //     padding:'20px',
// //     borderRadius:'5px',
// //     marginBottom:'20px',
// //   },
// //   form: {
// //     display: 'flex',
// //     flexDirection:'column',
// //   },
// //   input:{
// //     marginBottom:'10px',
// //     padding:'5px',
// //   },
// //   button: {
// //     padding:'10px',
// //     margin:'5px',
// //     cursor:'pointer',
// //   },

// // }

// // export default PetProfilePage;