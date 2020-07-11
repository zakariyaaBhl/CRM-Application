package com.bhl.crm.dao;

import com.bhl.crm.entities.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
