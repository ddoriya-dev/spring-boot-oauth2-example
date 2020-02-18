package com.acompany.bsystem.service;

import com.acompany.bsystem.domain.User;

import java.util.Optional;

public interface UserService {
	//
	Optional<User> getUser(String userName);

	boolean updateTokenId(String userName, String token);

}