package com.vicentemartinez.microserviceusermanagement.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vicentemartinez.microserviceusermanagement.model.Role;
import com.vicentemartinez.microserviceusermanagement.model.User;
import com.vicentemartinez.microserviceusermanagement.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/service/registration")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		if (userService.findByUserName(user.getName()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		user.setRole(Role.USER);
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}

	@GetMapping("/service/login")
	public ResponseEntity<?> getUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		if (principal == null || principal.getName() == null) {
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		return ResponseEntity.ok(userService.findByUserName(principal.getName()));
	}
	
	@PostMapping("/service/names")
	public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList){
		return ResponseEntity.ok(userService.findUser(idList));
	}
	
	@GetMapping("/service/test")
	public ResponseEntity<?> test(){
		return ResponseEntity.ok("It is working");
	}
}
