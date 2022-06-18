package mk.ukim.finki.hardwareshop.service;

import mk.ukim.finki.hardwareshop.model.User;
import mk.ukim.finki.hardwareshop.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username,
                  String password,
                  String repeatPassword,
                  String name,
                  String surname,
                  Role role);
}
