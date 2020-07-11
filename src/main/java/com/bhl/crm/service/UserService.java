package com.bhl.crm.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.bhl.crm.entities.User;
import com.bhl.crm.user.CrmUser;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);
    public void save(CrmUser crmUser);
}
