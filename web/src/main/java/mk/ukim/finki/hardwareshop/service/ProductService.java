package mk.ukim.finki.hardwareshop.service;

import mk.ukim.finki.hardwareshop.model.Category;
import mk.ukim.finki.hardwareshop.model.Manufacturer;
import mk.ukim.finki.hardwareshop.model.Product;
import mk.ukim.finki.hardwareshop.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByManufacturer(Manufacturer manufacturer);

    Product findByName(String name);

    Optional<Product> save(String name,
                           String description,
                           Double price,
                           Integer quantity,
                           Long category,
                           Long manufacturer);

    Optional<Product> save(ProductDto productDto);

    Product save(Product product);

    Optional<Product> edit(Long id,
                           String name,
                           String description,
                           Double price,
                           Integer quantity,
                           Long category,
                           Long manufacturer);

    Optional<Product> edit(Long id, ProductDto productDto);

    void deleteById(Long id);

}
