package com.leron.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leron.exceptions.UserNotFoundException;
import com.leron.models.User;
import com.leron.models.templates.UserLogin;
import com.leron.models.templates.UserRegistration;
import com.leron.repositories.UserRepository;
import com.leron.service.UserService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository dao;

	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> u = dao.findAll();
		
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
	public ResponseEntity<User> login (@PathVariable(name ="login") UserLogin ul) {
		User u = userService.login(ul);
		 if(u == null) {
			 return ResponseEntity.noContent().build();
		 }
		 return ResponseEntity.accepted().body(u);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> findById(@PathVariable(name = "id") Long id){
		Optional<User> u = userService.getUserById(id);
		return ResponseEntity.ok(u);
	}

}


	

