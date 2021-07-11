package com.bugt.reva.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bugt.reva.model.LoginModel;

@RestController
@RequestMapping
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	
	//@PostMapping("/login")
	@PostMapping(path = "/login", consumes = "application/x-www-form-urlencoded")
	public ModelAndView loginProcess(@RequestBody @ModelAttribute("loginModel") LoginModel loginModel){
		String userName=loginModel.getUsername();
		String password=loginModel.getPassword();
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName, password);
		Authentication authentication= authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		ModelAndView model=new ModelAndView("redirect:"+"/");
		return model;
		//return new ResponseEntity<>("",HttpStatus.OK);
	}
	
	@GetMapping("/customlogin2")
	public ModelAndView firstPage() {
		return new ModelAndView("customlogin2");
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
