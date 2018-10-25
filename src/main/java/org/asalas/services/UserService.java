package org.asalas.services;

import java.util.List;

import org.asalas.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void save(User user);
    public User findById(Integer id);
    public User findByUsername(String username);
    public List<User> listAll();
    public String delId(Integer id);
    public Long getSize();
    public void modEncPass(User u);
}