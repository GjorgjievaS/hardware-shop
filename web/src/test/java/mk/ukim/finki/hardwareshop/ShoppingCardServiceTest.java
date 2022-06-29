package mk.ukim.finki.hardwareshop;

import mk.ukim.finki.hardwareshop.repository.ShoppingCartRepository;
import mk.ukim.finki.hardwareshop.repository.UserRepository;
import mk.ukim.finki.hardwareshop.service.ProductService;
import mk.ukim.finki.hardwareshop.service.impl.ShoppingCartServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCardServiceTest {
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductService productService;

    private ShoppingCartServiceImpl service;

    @Before
    public void init() {
//        MockitoAnnotations.initMocks(this);
//        //User user = new User("username", "Vladimir", "Jovchev", "password", ROLE_USER);
//        Product product = new Product("name", "description", 299.99, 1, new Category(), new Manufacturer("test"));
//        Product.class;
//        Mockito.when(this.productService.save((Product.class))).thenReturn(product);
//
//
//        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
//        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");
//        this.service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }
}
