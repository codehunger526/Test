package com.agosh.account.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agosh.account.test.entity.UserEntity;
import com.agosh.account.test.exception.RecordNotFoundException;
import com.agosh.account.test.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/createUser")
	public String createUser(@Valid @RequestBody UserEntity userEntity) throws RecordNotFoundException {
		if (userService.isDOBValid(userEntity))
			userService.createOrUpdateUser(userEntity);
		else return "DOB must be in past";
		
		return "User created successfully!";
	}

	@PutMapping("/updateUser")
	public String updateUser(@RequestBody UserEntity userEntity) throws RecordNotFoundException {
		userService.createOrUpdateUser(userEntity);
		return "User updated successfully!";
	}
	
	@GetMapping("/getUser/{userId}")
	public String getUserById(@PathVariable("userId") Long id) throws RecordNotFoundException {
		userService.getUserById(id);
		return "User deleted succefully";
	}

	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUserById(@PathVariable("userId") Long id) throws RecordNotFoundException {
		userService.deleteUserById(id);
		return "User deleted succefully";
	}

}
