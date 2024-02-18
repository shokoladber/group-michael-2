import React, { useState } from 'react';
import './BuyCourses.css'


function Purchase() {
  const [selectedCourse, setSelectedCourse] = useState(null);
  const [paymentSuccess, setPaymentSuccess] = useState(false);

  const handlePurchase = () => {
    // Put some logic to process payment and mark purchase as successful
    // It can be an API call or any other method to handle payment processing
    // For demo purp- simulate a successful payment after 2 seconds
    setTimeout(() => {
      setPaymentSuccess(true);
    }, 2000);
  };

  return (
    <div className="purchase-container">
      {paymentSuccess ? (
        <div className="purchase-success">
          <h2>Payment Successful!</h2>
          <p>Thank you for purchasing {selectedCourse} course.</p>
        </div>
      ) : (
        <div className="purchase-form">
          <h2>Purchase Courses for Dog Training</h2>
          <label>Select Course:</label>
          <select
            className="class-select"
            value={selectedCourse}
            onChange={(e) => setSelectedCourse(e.target.value)}
          >
            <option value="">Select a course...</option>
            <option value="Beginner Obedience">Beginner Obedience</option>
            <option value="Advanced Agility">Advanced Agility</option>
            <option value="Puppy Socialization">Puppy Socialization</option>
          </select>
          <button className="purchase-button" onClick={handlePurchase}>
            Purchase
          </button>
        </div>
      )}
    </div>
  );
}

export default Purchase;
