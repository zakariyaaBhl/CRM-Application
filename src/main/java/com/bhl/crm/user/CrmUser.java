package com.bhl.crm.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CrmUser {
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String username;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String password;

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
	
	
}
