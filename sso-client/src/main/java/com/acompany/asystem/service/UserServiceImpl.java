package com.acompany.asystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acompany.asystem.domain.User;

@Service
public class UserServiceImpl implements UserService {
	//
	@Autowired
	private UserRepository repository;
	
	@Override
	public User getUser(String userName) {
		//
		return repository.findOne(userName);
	}
	
	@Override
	public boolean updateTokenId(String userName, String tokenId) {
		//
		User user = repository.findOne(userName);
		user.setTokenId(tokenId);
		
		repository.save(user);
		return true;
	}
}
