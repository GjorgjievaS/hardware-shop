package mk.ukim.finki.hardwareshop.rest;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.Value;
import mk.ukim.finki.hardwareshop.model.ChargeRequest;
import mk.ukim.finki.hardwareshop.model.Product;
import mk.ukim.finki.hardwareshop.model.dto.ProductDto;
import mk.ukim.finki.hardwareshop.service.ProductService;
import mk.ukim.finki.hardwareshop.service.ShoppingCartService;
import mk.ukim.finki.hardwareshop.service.StripeService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;
    private final StripeService paymentService;

    public ProductRestController(ProductService productService,StripeService paymentService,ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.paymentService = paymentService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    private List<Product> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> findById(@PathVariable Long id) {
        return this.productService.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto) {
        return this.productService.save(productDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> edit(@PathVariable Long id,
                                        @RequestBody ProductDto productDto) {
        return this.productService.edit(id, productDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
        if (this.productService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

   // @Value("")
    private String stripePublicKey;

    // REST
    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model, HttpServletRequest req) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentService.charge(chargeRequest);
        this.shoppingCartService.clearShoppingCartAfterPayment(req.getRemoteUser());
        return "redirect:/shopping-cart?orderConfirmation="+charge.getStatus();
    }

}
