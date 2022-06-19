import { Grid, Button, Typography, Alert, Snackbar } from "@mui/material";
import * as React from "react";
import { useParams } from "react-router-dom";
import { ProductRepository } from "./ProductRepository";
import { ShoppingCartRepository } from "./ShoppingCartRepository";

export default function ProductDetailPage() {
  const params = useParams();
  const [data, setData] = React.useState();
  const [success, setSuccess] = React.useState(false);
  const [error, setError] = React.useState(false);

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

  const addToCart = () => {
    ShoppingCartRepository.addToCart(data, "admin")
      .then((response) => {
        console.log(response);
        setSuccess(true);
      })
      .catch((error) => {
        console.log(error);
        setError(true);
      });
  };

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
            <Typography style={{ margin: "2%" }} variant="body1">
              {data?.description}
            </Typography>
            <Typography style={{ margin: "2%" }} color="teal" variant="h6">
              {numberFormat(data?.price)}
            </Typography>
            {data?.quantity > 0 && (
              <Button variant="contained" color="primary" onClick={addToCart}>
                Add to Cart
              </Button>
            )}
          </Grid>
        </Grid>
        <Snackbar
          open={success}
          autoHideDuration={3000}
          onClose={() => {
            setSuccess(false);
          }}
          anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        >
          {success && (
            <Alert
              onClose={() => {
                setSuccess(false);
              }}
              severity="success"
              sx={{ width: "100%" }}
            >
              Product successfully added to cart.
            </Alert>
          )}
        </Snackbar>
        <Snackbar
          open={error}
          autoHideDuration={3000}
          onClose={() => {
            setError(false);
          }}
          anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        >
          {error && (
            <Alert
              onClose={() => {
                setError(false);
              }}
              severity="error"
              sx={{ width: "100%" }}
            >
              Product already in cart.
            </Alert>
          )}
        </Snackbar>
      </div>
    </>
  );
}
