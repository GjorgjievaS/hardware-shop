package mk.ukim.finki.hardwareshop;

import mk.ukim.finki.hardwareshop.model.Category;
import mk.ukim.finki.hardwareshop.model.Manufacturer;
import mk.ukim.finki.hardwareshop.model.Product;
import mk.ukim.finki.hardwareshop.service.CategoryService;
import mk.ukim.finki.hardwareshop.service.ManufacturerService;
import mk.ukim.finki.hardwareshop.service.ProductService;

public class ImportData {
    public static void importManufacturers(ManufacturerService manufacturerService) {
        Manufacturer manufacturer1 = new Manufacturer("Samsung");
        Manufacturer manufacturer2 = new Manufacturer("Apple");
        Manufacturer manufacturer3 = new Manufacturer("Xiaomi");
        Manufacturer manufacturer4 = new Manufacturer("Huawei");
        Manufacturer manufacturer5 = new Manufacturer("JBL");
        if (manufacturerService.findByName(manufacturer1.getName()) == null) {
            manufacturerService.save(manufacturer1);
        }
        if (manufacturerService.findByName(manufacturer2.getName()) == null) {
            manufacturerService.save(manufacturer2);
        }
        if (manufacturerService.findByName(manufacturer3.getName()) == null) {
            manufacturerService.save(manufacturer3);
        }
        if (manufacturerService.findByName(manufacturer4.getName()) == null) {
            manufacturerService.save(manufacturer4);
        }
        if (manufacturerService.findByName(manufacturer5.getName()) == null) {
            manufacturerService.save(manufacturer5);
        }
    }


    public static void importCategories(CategoryService categoryService) {
        Category category1 = new Category("Smartphones", "Smartphones");
        Category category2 = new Category("Laptops", "Laptops");
        Category category3 = new Category("Tablets", "Tablets");
        Category category4 = new Category("Accessories", "Accessories");
        if (categoryService.findByName(category1.getName()) == null) {
            categoryService.save(category1);
        }
        if (categoryService.findByName(category2.getName()) == null) {
            categoryService.save(category2);
        }
        if (categoryService.findByName(category3.getName()) == null) {
            categoryService.save(category3);
        }
        if (categoryService.findByName(category4.getName()) == null) {
            categoryService.save(category4);
        }
    }

    public static void importProducts(ProductService productService, ManufacturerService manufacturerService, CategoryService categoryService) {
        Product product1 = new Product();
        product1.setName("Samsung Galaxy S9");
        product1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et " +
                "dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " + "laboris nisi ut aliquip.");
        product1.setPrice(1000.00);
        product1.setManufacturer(manufacturerService.findByName("Samsung"));
        product1.setCategory(categoryService.findByName("Smartphones"));
        if (productService.findByName(product1.getName()) == null) {
            productService.save(product1);
        }

        Product product2 = new Product();
        product2.setName("Xiaomi Mi Notebook");
        product2.setDescription("Xiaomi Mi Notebook");
        product2.setPrice(900.00);
        product2.setManufacturer(manufacturerService.findByName("Xiaomi"));
        product2.setCategory(categoryService.findByName("Laptops"));
        if (productService.findByName(product2.getName()) == null) {
            productService.save(product2);
        }

        Product product3 = new Product();
        product3.setName("JBL T20");
        product3.setDescription("JBL T20");
        product3.setPrice(200.00);
        product3.setManufacturer(manufacturerService.findByName("JBL"));
        product3.setCategory(categoryService.findByName("Accessories"));
        if (productService.findByName(product3.getName()) == null) {
            productService.save(product3);
        }

        Product product4 = new Product();
        product4.setName("Ipad Pro");
        product4.setDescription("Ipad Pro");
        product4.setPrice(1000.00);
        product4.setManufacturer(manufacturerService.findByName("Apple"));
        product4.setCategory(categoryService.findByName("Tablets"));
        if (productService.findByName(product4.getName()) == null) {
            productService.save(product4);
        }
    }
}
