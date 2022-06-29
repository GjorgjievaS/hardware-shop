package mk.ukim.finki.hardwareshop;

import mk.ukim.finki.hardwareshop.model.User;
import mk.ukim.finki.hardwareshop.model.enumerations.Role;
import mk.ukim.finki.hardwareshop.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.hardwareshop.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.hardwareshop.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.hardwareshop.repository.UserRepository;
import mk.ukim.finki.hardwareshop.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static mk.ukim.finki.hardwareshop.model.enumerations.Role.ROLE_USER;

@RunWith(MockitoJUnitRunner.class)
    public class UserRegistrationTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "Vladimir", "Jovchev", "password", ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");
        this.service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }
    @Test
    public void testSuccessRegister() {
        //String username, String password, String repeatPassword, String name, String surname, Role userRole
        User user = this.service.register("username",
                "password",
                "password",
                "Vladimir",
                "Jovchev",
                ROLE_USER);
        Mockito.verify(this.service).register("username",
                "password",
                "password",
                "Vladimir",
                "Jovchev",
                ROLE_USER);
        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("Name not equal", "Vladimir", user.getName());
        Assert.assertEquals("Surname not equal", "Jovchev", user.getSurname());
        Assert.assertEquals("Password not equal", "password", user.getPassword());
        //Assert.assertEquals("Repet Password not equal","password", user.getPassword());
        Assert.assertEquals("Role not equal", ROLE_USER, user.getRole());
    }
    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidUsernameOrPasswordException",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(null,
                        "password",
                        "password",
                        "Vladimir",
                        "Jovchev",
                        ROLE_USER));
        Mockito.verify(this.service).register(null,
                "password",
                "password",
                "Vladimir",
                "Jovchev",
                ROLE_USER);
    }
    @Test
    public void testEmptyPassword() {
        Assert.assertThrows("InvalidUsernameOrPasswordException",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register("username",
                        "",
                        "",
                        "Vladimir",
                        "Jovchev",
                        ROLE_USER));
        Mockito.verify(this.service).register("username",
                "",
                "",
                "Vladimir",
                "Jovchev",
                ROLE_USER);
    }
    @Test
    public void testNullPassword() {
        Assert.assertThrows("InvalidUsernameOrPasswordException",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register("username",
                        null,
                        null,
                        "Vladimir",
                        "Jovchev",
                        ROLE_USER));
        Mockito.verify(this.service).register("username",
                null,
                null,
                "Vladimir",
                "Jovchev",
                ROLE_USER);
    }
    @Test
    public void testEmptyUsername() {
        Assert.assertThrows("InvalidUsernameOrPasswordException",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register("",
                        "password",
                        "password",
                        "Vladimir",
                        "Jovchev",
                        ROLE_USER));

        Mockito.verify(this.service).register("",
                "password",
                "password",
                "Vladimir",
                "Jovchev",
                ROLE_USER);
    }
    @Test
    public void testPasswordMissmatch() {
        String username = "username";
        String password = "password";
        String repeatPassword = "newPassword";

        Assert.assertThrows("Password Missmatch",
                PasswordsDoNotMatchException.class, () -> this.service.register(
                        username,
                        password,
                        repeatPassword,
                        "Vladimir",
                        "Jovchev",
                        ROLE_USER));
        Mockito.verify(this.service).register(username,
                password,
                repeatPassword,
                "Vladimir",
                "Jovchev",
                ROLE_USER);
    }
    @Test
    public void testDuplicateUsername() {
        User user = new User("username",
                "Vladimir",
                "Jovchev",
                "password",
                Role.ROLE_USER);

        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "password", "password", "Vladimir", "Jovchev", Role.ROLE_USER));
        Mockito.verify(this.service).register(username, "password", "password", "Vladimir", "Jovchev", Role.ROLE_USER);
    }
}