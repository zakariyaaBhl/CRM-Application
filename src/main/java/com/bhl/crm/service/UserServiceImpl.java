package com.bhl.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bhl.crm.dao.RoleDao;
import com.bhl.crm.dao.UserDao;
import com.bhl.crm.entities.Role;
import com.bhl.crm.entities.User;
import com.bhl.crm.user.CrmUser;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional("transactionManagerforUser")
public class UserServiceImpl implements UserService {

	// need to inject user & role dao 
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	public void save(CrmUser crmUser) {
		User user = new User();
		//user.setId(userDao.maxIdinTable()+1);
		// assign user details to the user object
		user.setUserName(crmUser.getUsername());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		 // save user in the database
		userDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
