package org.asalas.boostrap;

import java.util.List;

import org.asalas.domain.Role;
import org.asalas.domain.User;
import org.asalas.services.RoleService;
import org.asalas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
/*
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UserService userServ;
	
	@Autowired
	private RoleService roleServ;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Init first user!");
		if( userServ.findByUsername("xavier") == null) {
		loadRoles();
		loadUsers();
		assignUsersToAdminRole();
			System.out.println("First user created!");
		}
	}
	private void loadRoles() {

        Role role = new Role();
        role.setRole("CUSTOMER");
        roleServ.saveOrUpdate(role);
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleServ.saveOrUpdate(adminRole);
    }
    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("xavier");
        user1.setPassword("xav123");
        
    }
    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleServ.listAll();
        List<User> users = (List<User>) userServ.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equalsIgnoreCase("bschuster")) {
                        user.addRole(role);
                        userServ.save(user);
                    }
                });
            }
        });
    }
}
*/