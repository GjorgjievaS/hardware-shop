package mk.ukim.finki.hardwareshop.service.impl;

import mk.ukim.finki.hardwareshop.model.Category;
import mk.ukim.finki.hardwareshop.model.Manufacturer;
import mk.ukim.finki.hardwareshop.model.Product;
import mk.ukim.finki.hardwareshop.model.dto.ProductDto;
import mk.ukim.finki.hardwareshop.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.hardwareshop.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.hardwareshop.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.hardwareshop.repository.CategoryRepository;
import mk.ukim.finki.hardwareshop.repository.ManufacturerRepository;
import mk.ukim.finki.hardwareshop.repository.ProductRepository;
import mk.ukim.finki.hardwareshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }


    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return this.productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findAllByManufacturer(Manufacturer manufacturer) {
        return this.productRepository.findAllByManufacturer(manufacturer);
    }

    @Override
    public Optional<Product> save(String name, String description, Double price,
                                  Integer quantity, Long categoryId, Long manufacturerId) {

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));


        this.productRepository.deleteByName(name);

        Product product = new Product(name, description, price, quantity, category, manufacturer);

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Optional<Product> save(ProductDto productDto) {

        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));

        this.productRepository.deleteByName(productDto.getName());

        Product product = new Product(productDto.getName(), productDto.getDescription(), productDto.getPrice(),
                productDto.getQuantity(), category, manufacturer);
        return Optional.of(this.productRepository.save(product));

    }

    @Override
    public Optional<Product> edit(Long id, String name, String description,
                                  Double price, Integer quantity, Long categoryId,
                                  Long manufacturerId) {

        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setManufacturer(manufacturer);

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {

        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));


        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(category);
        product.setManufacturer(manufacturer);

        return Optional.of(this.productRepository.save(product));

    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
