package com.bhl.crm.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class CrmUser {
	
	@NotNull
	@Size(min=5)
	private String username;
	
	@NotNull
	@Size(min=2)
	private String password;

	@NotNull
	@Size(min = 5)
	private String firstName;

	@NotNull
	@Size(min = 5)
	private String lastName;

	@NotNull
	@Size(min = 5, message = "is required")
	private String email;
	
	
	
	
	/*-- Getters & Setters --*/
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	/*-- Constructors --*/
	public CrmUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CrmUser(@NotNull(message = "is required") @Size(min = 1, message = "is required") String username,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public CrmUser(@NotNull(message = "is required") @Size(min = 5) String username,
			@NotNull(message = "is required") @Size(min = 5) String password,
			@NotNull(message = "is required") @Size(min = 5) String firstName,
			@NotNull(message = "is required") @Size(min = 5) String lastName,
			@NotNull(message = "is required") @Size(min = 5, message = "is required") String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
	
}
