package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CustomException;
import com.model.User;
import com.service.UserService;

@RestController
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/getusers")
	public ResponseEntity<?> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/getuser/{username}")
	public ResponseEntity<?> getUser(@PathVariable String username) throws CustomException {
		User user;
		try {
			user = userService.getUser(username);
		}catch(Exception e) {
			throw new CustomException("User not found with name : "+username);
		}
		return new ResponseEntity<>(user,HttpStatus.FOUND);
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<?> addUser(@RequestBody User user) throws CustomException{
		try {
			userService.addUser(user);
		}catch(CustomException e) {
			throw new CustomException(e.getMessage());
		}
		
		return new ResponseEntity<>("User added successfully",HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteuser/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username){
		userService.deleteUser(username);
		return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
	}
}
