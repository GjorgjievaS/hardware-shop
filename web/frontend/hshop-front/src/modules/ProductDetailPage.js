import {
  Grid,
  Button,
  Typography,
} from "@mui/material";
import * as React from "react";
import { useParams } from "react-router-dom";
import { ProductRepository } from "./ProductRepository";

export default function ProductDetailPage() {
  const params = useParams();
  const [data, setData] = React.useState();

  const numberFormat = (value) =>
    new Intl.NumberFormat("en-gb", {
      style: "currency",
      currency: "EUR",
    }).format(value);

  React.useEffect(() => {
    if (params?.id) {
      ProductRepository.getById(params.id)
        .then((response) => {
          console.log(response.data);
          setData(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [params.id]);

  return (
    <>
      <div style={{ margin: "5%" }}>
        <Grid container spacing={2}>
          <Grid item xs={6} md={6}>
            <img
              style={{ width: "90%" }}
              src="https://data.telering.co.id/riv-content/uploads/modules/others/notfound.jpg"
              alt="product-img"
            />
          </Grid>
          <Grid item xs={6} md={6}>
            {data?.quantity === 0 && (
              <Button
                style={{ color: "red" }}
                disabled="true"
                variant="h6"
                gutterBottom
              >
                Out of Stock
              </Button>
            )}
            <Typography variant="h4">{data?.name}</Typography>
            <Typography variant="body1">{data?.description}</Typography>
            <Typography color="teal" variant="h6">
              {numberFormat(data?.price)}
            </Typography>
            {data?.quantity > 0 && (
              <Button variant="contained" color="primary">
                Add to Cart
              </Button>
            )}
          </Grid>
        </Grid>
      </div>
    </>
  );
}
