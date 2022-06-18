package mk.ukim.finki.hardwareshop.repository;

import mk.ukim.finki.hardwareshop.model.Category;
import mk.ukim.finki.hardwareshop.model.Manufacturer;
import mk.ukim.finki.hardwareshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByManufacturer(Manufacturer manufacturer);

    Product findByName(String name);

    void deleteByName(String name);

}
