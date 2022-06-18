package mk.ukim.finki.hardwareshop;
///*
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication*/
import mk.ukim.finki.hardwareshop.service.CategoryService;
import mk.ukim.finki.hardwareshop.service.ManufacturerService;
import mk.ukim.finki.hardwareshop.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class HardwareShopApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HardwareShopApplication.class, args);
        ManufacturerService manufacturerService = (ManufacturerService) context.getBean("manufacturerServiceImpl");
        CategoryService categoryService = (CategoryService) context.getBean("categoryServiceImpl");
        ProductService productService = (ProductService) context.getBean("productServiceImpl");
        ImportData.importManufacturers(manufacturerService);
        ImportData.importCategories(categoryService);
        ImportData.importProducts(productService, manufacturerService, categoryService);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
