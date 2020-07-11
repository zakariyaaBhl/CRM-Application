package com.bhl.crm.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bcpe;
	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource).passwordEncoder(bcpe);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.formLogin(); http.authorizeRequests().anyRequest().authenticated().and()
		 * .logout().permitAll();
		 */
		
		http
			.authorizeRequests().antMatchers("/","/show").hasAnyRole("ADMIN","EMPLOYEE","MANAGER")
			.antMatchers("/addForm","/addProduct","/deleteProd","/updateForm","/updateProduct").hasRole("ADMIN").and()
			.formLogin().loginPage("/login").loginProcessingUrl("/authenticatedTheUser").permitAll().and()
			.logout().permitAll().and()
			.exceptionHandling().accessDeniedPage("/accessDenied");
		
		
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	/*-- Create a UserDetailsManager bean for manage our bean
	 * 		create a JDBC User Details Manager bean. This is based on our security datasource. 
	 * 		It provides access to the database for creating users. We'll also use JdbcUserDetailsManager to check if a user exists.
	 * 		The JdbcUserDetailsManager has all of the low-level JDBC code for accessing the security database. 
	 * 		There is no need for us to create the JDBC code JdbcUserDetailsManager will handle it for us
	 *  --*/
	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager= new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		return jdbcUserDetailsManager;
	}
}
