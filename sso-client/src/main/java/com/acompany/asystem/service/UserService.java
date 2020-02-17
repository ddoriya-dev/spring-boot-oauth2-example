package com.acompany.asystem.service;

import com.acompany.asystem.domain.User;

import java.util.Optional;

public interface UserService {
	//
	Optional<User> getUser(String userName);

	boolean updateTokenId(String userName, String token);

}