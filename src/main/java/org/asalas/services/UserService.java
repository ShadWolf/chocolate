package org.asalas.services;

import java.util.List;

import org.asalas.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void save(User user);

    public User findByUsername(String username);
    public List<User> listAll();
    public void delId(Integer id);
    public Long getSize();
}