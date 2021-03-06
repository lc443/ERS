package com.leron.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leron.models.User;
import com.leron.models.templates.UserLogin;
import com.leron.models.templates.UserRegistration;
import com.leron.repositories.UserRepository;
import com.leron.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository dao;
	


	
	@GetMapping("/employees")
	public ResponseEntity<List<User>> findAll() {
		List<User> u = userService.findAll();
		
		return ResponseEntity.ok(u);
	}

	@PostMapping("/signup") 
	public ResponseEntity<User> signup(@RequestBody UserRegistration ur) {
			User user = userService.register(ur);
			if(dao.existsById(user.getId())) {
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.accepted().body(user);
		}
	
	
	@GetMapping("/login")
	public ResponseEntity<User> login (@PathVariable(name = "login") @RequestBody UserLogin ul) {
		User u = userService.login(ul);
		 if(u == null) {
			 return ResponseEntity.noContent().build();
		 }
		 return ResponseEntity.accepted().body(u);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> findUserById(@PathVariable(name = "id") Long id){
		Optional<User> u = userService.getUserById(id);
		return ResponseEntity.ok(u);
	}
	
	@PatchMapping("/{update}") 
	public ResponseEntity updateUser(@RequestBody User u) {
		if(userService.update(u) == true) {
			return ResponseEntity.accepted().build();
		} 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}

}


	

