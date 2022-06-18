import Axios from "axios";

export const ProductRepository = {
  getById: (id) => {
    return Axios({
      url: `http://localhost:8080/api/products/${id}`,
      method: "GET",
    });
  },
};
