import "./App.css";
import { Route, Routes } from "react-router-dom";
import ProductDetailPage from "./modules/ProductDetailPage";
import React from "react";

function App() {
  return (
    <>
      <Routes>
        {/* <Route path="/" element={<HomeComponent />} /> */}
        {/* <Route path="/login" element={<Login />} /> */}
        <Route path="/product-details" element={<ProductDetailPage />}>
          <Route path=":id" element={<ProductDetailPage />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
