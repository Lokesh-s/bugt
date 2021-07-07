package com.bugt.reva.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bugt.reva.model.Permission;
import com.bugt.reva.model.User;
import com.bugt.reva.service.PermissionService;
import com.bugt.reva.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PermissionService permissionService;
	
	@GetMapping("/allUsers/{currentUser}")
	public ResponseEntity<List<String>> getAllUsers(@PathVariable("currentUser") String currentUser){
		try {
			List<String> userList = new ArrayList<String>();
			List<User> allUsers=userService.findAll();
			
			if (allUsers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				for (int i = 0; i < allUsers.size(); i++) {
					User user=allUsers.get(i);
					if (!user.getUserName().equalsIgnoreCase(currentUser)) {
						userList.add(user.getUserName());
					}
				}
			}
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User savedUser = userService.save(user);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> userdata = userService.findById(id);
		if (userdata.isPresent()) {
			return new ResponseEntity<>(userdata.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteBug(@PathVariable("id") long id) {
		try {
			userService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user/bugstatus/{role}")
	public ResponseEntity<List<String>> bugStatus(@PathVariable("role") String role) {
		Permission permission=permissionService.getRoleStatus(role);
		String roleStatus=permission.getStatusList();
		List<String> defectStatusList=Arrays.asList(roleStatus.split(","));
		if (!roleStatus.isEmpty()) {
			return new ResponseEntity<>(defectStatusList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/bugstatus")
	public ResponseEntity<Permission> addBugStatus() {
		Permission permission = new Permission();
		List<String> statusList=new ArrayList<>();
		statusList.add("Open");
		statusList.add("Closed");
		permission.setRoleName("Manager");
		permission.setStatusList("Open,Closed");
		return new ResponseEntity<>(permissionService.save(permission), HttpStatus.OK);
	}

}