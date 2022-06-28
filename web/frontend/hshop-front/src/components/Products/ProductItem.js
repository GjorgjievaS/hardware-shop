import React from 'react';

import Card from '../UI/Card';
import './ProductItem.css';

const ProductItem = (props) => {
  return (
   
      
       <Card className='cart-items'>
         <div class="image-box">
                    <img src={props.image} />
                  </div>
                  <div class="product-info">
                  <div class="about">
                   <h2 class="title">{props.title}</h2>
                  </div>
                  <div class="counter"> 
                    <div class="count">Qty: {props.qty}</div>
                  </div>
                  <div class="prices">
                    <div class="amount">${props.amount}</div>
                    <div class="remove"><a href="#">Remove</a></div>
                  </div>
                  </div>

    </Card>
    
  );
}

export default ProductItem;
