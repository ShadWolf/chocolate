package org.asalas.controllers;

import java.util.List;

import org.asalas.domain.Role;
import org.asalas.domain.User;
import org.asalas.forms.MsgForm;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	private String varMsg = "";
	private String varErr = "";

	@RequestMapping("/users")
	public String showUsers(ModelMap model) {
		User user = new User();
		model.addAttribute("page", "users");
		model.addAttribute("varList", userService.listAll());
		model.addAttribute("varForm", user);
		return "mainpage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@PostMapping("/usermod")
	public String modUser(@ModelAttribute User u, RedirectAttributes redirectAttributes) {
		varErr = null;
		varMsg = "Utilisateur " + u.getUsername() + " modifie avec succes!";
		userService.modEncPass(u);
		MsgForm f = new MsgForm();
		f.setNextpage("users");
		f.setMsg(varMsg);
		redirectAttributes.addFlashAttribute("f", f);
		return "redirect:/msg";
	}

	@PostMapping("/useradd")
	public String addUser(@ModelAttribute User u, RedirectAttributes redirectAttributes) {
		varMsg = "Utilisateur " + u.getUsername() + " ajoute avec succes!";
		varErr = null;
		userService.save(u);
		MsgForm f = new MsgForm();
		f.setNextpage("users");
		f.setMsg(varMsg);
		redirectAttributes.addFlashAttribute("f", f);
		return "redirect:/msg";
	}

	@GetMapping("/usersdel/{id}")
	public String delUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
		Long count = userService.getSize();
		varMsg = null;
		varErr = null;
		if (count > 1) {
			varMsg = "Utilisteur " + userService.delId(Integer.valueOf(id)) + " supprime avec succes!";

		} else {
			varErr = "Impossible de supprimer Minimum 1";
		}
		MsgForm f = new MsgForm();
		f.setNextpage("users");
		if (varMsg != null) {
			f.setMsg(varMsg);
		}
		if (varErr != null) {
			f.setErr(varErr);
		}
		redirectAttributes.addFlashAttribute("f", f);
		return "redirect:/msg";
	}

	/* Init the first user if needed */
	@RequestMapping(value = { "/first_user" }, method = RequestMethod.GET)
	private void first_user() {
		System.out.println("Init first user!");
		if (userService.findByUsername("xavier") == null) {
			loadRoles();
			loadUsers();
			assignUsersToAdminRole();
			System.out.println("First user created!");
		} else {
			System.out.println("no need to do this!");
		}

	}

	private void loadRoles() {

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

	private void assignUsersToAdminRole() {
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
