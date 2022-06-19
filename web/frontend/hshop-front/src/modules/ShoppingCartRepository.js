import Axios from "axios";

export const ShoppingCartRepository = {
  addToCart: (product, username) => {
    return Axios({
      url: `http://localhost:8080/api/shopping-cart/add`,
      data: product,
      method: "POST",
      params: {
        username: username,
      }
    });
  },
};
