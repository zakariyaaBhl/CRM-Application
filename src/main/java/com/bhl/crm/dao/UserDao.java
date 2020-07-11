package com.bhl.crm.dao;

import com.bhl.crm.entities.User;

public interface UserDao {

    public User findByUserName(String userName);
    public void save(User user);
    public void update(User user);
    public void delete(Long id);
    public Long maxIdinTable();
	
    
}
