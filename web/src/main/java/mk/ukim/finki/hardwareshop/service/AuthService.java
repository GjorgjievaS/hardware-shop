package mk.ukim.finki.hardwareshop.service;

import mk.ukim.finki.hardwareshop.model.User;

public interface AuthService {

    User login(String username, String password);
}

