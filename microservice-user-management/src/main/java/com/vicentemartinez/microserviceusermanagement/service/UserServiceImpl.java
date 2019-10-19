package com.vicentemartinez.microserviceusermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vicentemartinez.microserviceusermanagement.model.User;
import com.vicentemartinez.microserviceusermanagement.repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//We will create bean for this
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}
	
	@Override
	public List<String> findUser(List<Long> idList){
		return userRepository.findByIdList(idList);
	}

}
