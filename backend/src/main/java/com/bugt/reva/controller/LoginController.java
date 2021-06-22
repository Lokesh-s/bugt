package com.bugt.reva.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class LoginController {
	
	@GetMapping("/index")
	public ModelAndView index(Principal principle) {
		ModelAndView model=new ModelAndView("redirect:"+"/");
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails=(UserDetails) authentication.getPrincipal();
		Map<String, Object> hashMap=new HashMap<>();
		hashMap.put("userName", userDetails.getUsername());
		hashMap.put("authorities", userDetails.getAuthorities());
		model.addObject(hashMap);
		return model;
	}
	
	@GetMapping("/userDetails")
	public ResponseEntity<Map<String, Object>> getUserDetails() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails=(UserDetails) authentication.getPrincipal();
		Map<String, Object> hashMap=new HashMap<>();
		hashMap.put("userName", userDetails.getUsername());
		hashMap.put("authorities", userDetails.getAuthorities());
		return new ResponseEntity<>(hashMap,HttpStatus.OK);
	}

}
