package org.asalas.controllers;


import java.util.List;

import org.asalas.domain.Role;
import org.asalas.domain.User;
import org.asalas.services.RoleService;
import org.asalas.services.SecurityService;
import org.asalas.services.UserService;
import org.asalas.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class UserController {
   @Autowired
    private UserService userService;

   @Autowired
   private RoleService roleService;
   
    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    

    @RequestMapping("/users")
    public String showUsers(ModelMap model) {
    	User user = new User(); 
    	model.addAttribute("page", "users");
    	model.addAttribute("varMsg", "");
    	model.addAttribute("varList", userService.listAll());
    	model.addAttribute("varForm", user );
    	
    	return "mainpage";
    }
    /*
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/dashboard";
    }
    */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
         return "login";
    }

  
    
    /*Init the first user if needed */
    @RequestMapping(value = {"/first_user"}, method = RequestMethod.GET)
    private void first_user() {
		System.out.println("Init first user!");
		if( userService.findByUsername("xavier") == null) {
		loadRoles();
		loadUsers();
		assignUsersToAdminRole();
			System.out.println("First user created!");
		} else {
			System.out.println("no need to do this!");
		}

	}
	private  void loadRoles() {

        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.saveOrUpdate(role);
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
    }
    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("xavier");
        // user1.setEncryptedPassword(bCryptPasswordEncoder.encode("xav123") );
        user1.setPassword("xav123");
        userService.save(user1);
    }
    private  void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equalsIgnoreCase("bschuster")) {
                        user.addRole(role);
                        userService.save(user);
                    }
                });
            }
        });
    }
}
