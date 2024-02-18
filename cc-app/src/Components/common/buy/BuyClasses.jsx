import React, { useState } from 'react';
import './BuyClasses.css'


function Purchase() {
  const [selectedClass, setSelectedClass] = useState(null);
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
          <p>Thank you for purchasing {selectedClass} class.</p>
        </div>
      ) : (
        <div className="purchase-form">
          <h2>Purchase Classes for Dog Training</h2>
          <label>Select Class:</label>
          <select
            className="class-select"
            value={selectedClass}
            onChange={(e) => setSelectedClass(e.target.value)}
          >
            <option value="">Select a class...</option>
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
