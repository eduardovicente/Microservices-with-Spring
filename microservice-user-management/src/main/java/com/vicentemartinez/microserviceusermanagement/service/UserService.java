package com.vicentemartinez.microserviceusermanagement.service;

import java.util.List;

import com.vicentemartinez.microserviceusermanagement.model.User;

public interface UserService {

	User save(User user);

	User findByUserName(String username);

	List<String> findUser(List<Long> idList);

}
