package com.leron.service;

import java.util.List;
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
	
	@Autowired
	private PasswordHashService pw;
	


	public UserService(UserRepository userDao) {
		this.userDao = userDao;
	}
	
	
	public User register(UserRegistration ur) {

		User newUser = new User(ur.getUsername(), pw.passwordHash(ur.getPassword()),
				ur.getFirstName(), ur.getLastName(), ur.getEmail(), ur.getRoleId());
		User savedUser = userDao.save(newUser);
		
		if(savedUser.getId() == 0) {
			log.info("Failed to register User");
			return null;
		}
		
		newUser.setId(savedUser.getId());
		
		log.info("Registered new User" );
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
	
	public boolean update(User u) {
				
		if(userDao.existsById(u.getId())) {
			 u  = new User(u.getFirstName(), u.getLastName(), u.getUsername(), pw.passwordHash(u.getPassword()), u.getEmail());
			userDao.save(u);
			log.info("User Updated Successfully");
			
			 return true;
		} 
		log.info("Could not update user");
			
		return false;
		
		
	}
	
 	public String authorName(int id) {

		User u = userDao.findById(id);
		
		if(u == null) {
			log.info("Could not find author");
			return null;
		}
		return u.getFirstName() + " " + u.getLastName();
	}
	
 	public boolean deleteUser(User u) {
 		if(userDao.existsById(u.getId())) {
 			userDao.delete(u);
 		
 			log.info("Deleted a User" );
 			return true;
 		}
 		
 		log.info("Could not delete user" );
 			return false;
 	}

 	public User findByUserName(User u) {
 		if(userDao.existsById(u.getId())) {
 			User user = userDao.findByUsername(u.getUsername());
 			return user;
 			
 		}
 		log.info("could not fin duser");
 		return null;
 	}

 	public List<User> findAll() {
 		List <User>  userList = userDao.findAll();
 			log.info("found users ");
 			return userList;
 		}
}