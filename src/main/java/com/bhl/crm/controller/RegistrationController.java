package com.bhl.crm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhl.crm.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	/*-- UserDetailsManager bean Injection --*/
	@Autowired
	private UserDetailsManager userDetailsManager;
	private BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
	
	@GetMapping("/showRegistrationForm")
	public String registrationForm(Model model) {
		model.addAttribute("crmUser" , new CrmUser());
		return "registrationForm";
	}
	
	@PostMapping("/processRegistrationForm")
	public String registration(Model model,@ModelAttribute("crmUser")@Valid CrmUser crmUser, BindingResult bindingResult) {
		// form validation
			if (bindingResult.hasErrors()) {
				model.addAttribute("crmUser" , crmUser);
				model.addAttribute("registrationError", "User name/password can not be empty.");
				return "registrationForm";
			}

		// check the database if user already exists
			boolean userExist = doesUserExist(crmUser.getUsername());
			if (userExist) {
				model.addAttribute("crmUser" , crmUser);
				model.addAttribute("registrationError", "User name already exists.");
				return "registrationForm";
			}
		// encrypt the password
			String encodedPassword = passwordEncoder.encode(crmUser.getPassword());
		
		// give user default role of "employee"
			List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
		// create user details object
			User tempUser = new User(crmUser.getUsername(), encodedPassword, authorities);
		// save user in the database
			userDetailsManager.createUser(tempUser);
			
			return "registrationConfirmation";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	private boolean doesUserExist(String userName) {
			// check the database if the user already exists
			boolean exists = userDetailsManager.userExists(userName);
		return exists;
	}
}
