package mk.ukim.finki.hardwareshop.service;

import mk.ukim.finki.hardwareshop.model.Product;
import mk.ukim.finki.hardwareshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);


    void clearShoppingCartAfterPayment(String username);
}
