package mk.ukim.finki.hardwareshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    public ShoppingCart() {
    }

    public ShoppingCart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }
}
