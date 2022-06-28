import React from 'react';

import './CheckoutSection.css';

const CheckoutSection = (props) => {
  const classes = 'checkoutSection ';

  return (
  <div class="card checkout">
      <p>Continue to checkout to enter your order details!</p>
      <a href="#" id="checkoutButton">Go to Checkout </a>
  </div>
  );
}

export default CheckoutSection;
