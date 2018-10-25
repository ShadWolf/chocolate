package org.asalas.services;
import org.asalas.domain.User;
import org.asalas.repo.RoleRepository;
import org.asalas.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        user.setRoles(roleRepository.findAll());
        
        userRepository .save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @Override
   public List<User> listAll(){
    	return userRepository.findAll();
    }
    @Override
    public String delId(Integer id) {
    	User u = findById(id);
    	String uname = u.getUsername();
    	userRepository.deleteById(id);
    	return uname;
    }
    @Override
    public Long getSize() {
    	return userRepository.count();
    }
    public void modEncPass(User u) {
    	String encpaswd = bCryptPasswordEncoder.encode(u.getPassword());
    	User b = findByUsername(u.getUsername());
    	userRepository.modEncPass(b.getId(), encpaswd);
    }
}
