package com.bugt.reva.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bugt.reva.model.User;
import com.bugt.reva.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<String>> getAllBugs(){
		try {
			List<String> userList = new ArrayList<String>();
			List<User> allUsers=userService.findAll();
			
			if (allUsers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				for (int i = 0; i < allUsers.size(); i++) {
					User user=allUsers.get(i);
					userList.add(user.getUserName());
				}
			}
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}