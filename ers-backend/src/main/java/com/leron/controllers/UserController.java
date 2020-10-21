package com.leron.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leron.models.User;
import com.leron.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository dao;
	
	@GetMapping
	public ResponseEntity< List<User> > findAll() {
		return ResponseEntity.ok(dao.findAll());
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User u) {
		if(dao.existsById(u.getId())) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.accepted().body(dao.save(u));
	}
	
}
