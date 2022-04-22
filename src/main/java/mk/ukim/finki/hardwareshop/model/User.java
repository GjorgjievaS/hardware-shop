package mk.ukim.finki.hardwareshop.model;

import lombok.Data;
import mk.ukim.finki.hardwareshop.model.enumerations.Role;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "hardware_shop_users")
public class User {

    @Id
    private String username;

    private String name;

    private String surname;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user",  fetch = FetchType.EAGER)
    private List<ShoppingCart> shoppingCarts;


    public User() {
    }

    public User(String username,
                String name,
                String surname,
                String password,
                Role role,
                List<ShoppingCart> shoppingCarts) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
        this.shoppingCarts = shoppingCarts;
    }
}
