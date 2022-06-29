package mk.ukim.finki.hardwareshop;

import mk.ukim.finki.hardwareshop.model.Category;
import mk.ukim.finki.hardwareshop.model.Manufacturer;
import mk.ukim.finki.hardwareshop.model.Product;
import mk.ukim.finki.hardwareshop.model.enumerations.Role;
import mk.ukim.finki.hardwareshop.service.CategoryService;
import mk.ukim.finki.hardwareshop.service.ManufacturerService;
import mk.ukim.finki.hardwareshop.service.ProductService;
import mk.ukim.finki.hardwareshop.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class HardwareShopApplicationTests {
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    private static Category category;
    private static Manufacturer manufacturer;
    private static boolean dataInit = false;

    public void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        initData();
    }
    private void initData() {
        if (dataInit == false) {
            category = categoryService.create("name_category", "description_category");
            categoryService.create("name_category_2", "description_category_2");
            //manufacturer = manufacturerService.save("manufacturer_name", "manufacturer_address");
            manufacturerService.save("manufacturer_name_2", "manufacturer_address_2");
            String user = "user";
            String admin = "admin";
            //userService.register("username", "password", "password", "Vladimir", "Jovchev", Role.ROLE_USER);
            userService.register(user, user, user, user, user, Role.ROLE_USER);
            userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInit = true;
        }
    }
    @Test
    void contextLoads() {
    }
    @Test
    public void testGetProducts() throws Exception {
        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/api/products");
        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "products"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));
    }
    @Test
    public void testDeleteProduct() throws Exception {
        Product product = this.productService.save("test", "description", 2.0, 2, category.getId(), manufacturer.getId()).get();
        MockHttpServletRequestBuilder productDeleteRequest = MockMvcRequestBuilders
                .delete("/delete/" + product.getId());

        this.mockMvc.perform(productDeleteRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/api/products"));
    }
}