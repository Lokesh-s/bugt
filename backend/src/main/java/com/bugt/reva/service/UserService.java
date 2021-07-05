package com.bugt.reva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bugt.reva.model.User;
import com.bugt.reva.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User save(User user) {
		user.setActive(true);
		return userRepository.save(user);
	}
	
	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}
	
	public void deleteById(long id) {
		userRepository.deleteById(id);
	}
}
