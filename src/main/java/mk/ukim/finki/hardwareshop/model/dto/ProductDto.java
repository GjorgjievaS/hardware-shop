package mk.ukim.finki.hardwareshop.model.dto;

import lombok.Data;
import mk.ukim.finki.hardwareshop.model.Category;
import mk.ukim.finki.hardwareshop.model.Manufacturer;

import javax.persistence.ManyToOne;

@Data
public class ProductDto {

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private Long category;

    private Long manufacturer;

    public ProductDto(String name,
                      String description,
                      Double price,
                      Integer quantity,
                      Long category,
                      Long manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }
}
