package mk.ukim.finki.hardwareshop.web.rest;

import mk.ukim.finki.hardwareshop.model.Category;
import mk.ukim.finki.hardwareshop.model.Product;
import mk.ukim.finki.hardwareshop.model.ShoppingCart;
import mk.ukim.finki.hardwareshop.model.User;
import mk.ukim.finki.hardwareshop.service.CategoryService;
import mk.ukim.finki.hardwareshop.service.ShoppingCartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody Product product, @RequestParam(value = "username") String username) {
        this.shoppingCartService.addProductToShoppingCart(product, username);
    }
}