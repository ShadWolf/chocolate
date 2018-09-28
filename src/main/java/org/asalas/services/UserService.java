package org.asalas.services;

import java.util.List;

import org.asalas.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);

    User findByUsername(String username);
    List<User> listAll();
}