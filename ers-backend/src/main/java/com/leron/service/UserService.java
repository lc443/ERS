package com.leron.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leron.exceptions.UserNotFoundException;
import com.leron.models.User;
import com.leron.models.templates.UserLogin;
import com.leron.models.templates.UserRegistration;
import com.leron.repositories.UserRepository;

@Service
public class UserService {

	private static Logger log = LoggerFactory.getLogger(UserService.class) ; 
	@Autowired
	private final UserRepository userDao;
	
	
	public UserService(UserRepository userDao) {
		this.userDao = userDao;
	}
	
	
	public User register(UserRegistration ur) {
		User newUser = new User(0, ur.getUsername(), ur.getPassword(),
				ur.getFirstName(), ur.getLastName(), ur.getEmail(), ur.getRoleId());
		User savedUser = userDao.save(newUser);
		
		if(savedUser.getId() == 0) {
			log.info("Failed to register User");
			return null;
		}
		
		newUser.setId(savedUser.getId());
		
		return newUser;
		
	}
	
	public User login(UserLogin ul) {
		User u = userDao.findByUsername(ul.getUsername());
		 if(u == null) {
			 log.info(" Logging failed: Could not find user." );
			 return null;
		 }
		 if(ul.getPassword().equals(u.getPassword())) {
			 log.info("Loggin in user");
			 return u;
		 }
		 
		 log.info("Loggin failed: Incorrect password");
		 return null;
	}
	
	public Optional<User> getUserById(long id) {
		Optional<User> user = userDao.findById(id);
		if(user == null) {
			log.info("Could not find user");
			throw new UserNotFoundException("Could not find User by id:" + id);
		}
		return user;
	}
	
	public String authorName(int id) {
		User u = userDao.findById(id);
		
		if(u == null) {
			log.info("Could not find author");
			return null;
		}
		return u.getFirstName() + " " + u.getLastName();
	}
	
}