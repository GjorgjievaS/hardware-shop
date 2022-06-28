import React, { useState } from 'react';

import ProductItem from './ProductItem';
import Card from '../UI/Card';
import './Products.css';

const Products = (props) => {

  return (
      <Card className='products'>
        
        {props.items.map((product) => (
          <ProductItem
            title={product.title}
            amount={product.amount}
            qty={product.qty}
            image={product.image}
          />
        ))}
      </Card>
  );
};

export default Products;
