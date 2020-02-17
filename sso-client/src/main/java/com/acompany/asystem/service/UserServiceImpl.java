package com.acompany.asystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acompany.asystem.domain.User;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	//
	@Autowired
	private UserRepository repository;
	
	@Override
	public Optional<User> getUser(String userName) {
		//
		return repository.findById(userName);
	}
	
	@Override
	public boolean updateTokenId(String userName, String tokenId) {
		//
		Optional<User> user = repository.findById(userName);
		user.get().setTokenId(tokenId);
		
		repository.save(user.get());
		return true;
	}
}
