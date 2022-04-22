package mk.ukim.finki.hardwareshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Manufacturer() {
    }

    public Manufacturer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
