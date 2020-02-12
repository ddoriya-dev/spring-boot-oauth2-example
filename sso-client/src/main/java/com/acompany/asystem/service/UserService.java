package com.acompany.asystem.service;

import com.acompany.asystem.domain.User;

public interface UserService {
	//
	User getUser(String userName);

	boolean updateTokenId(String userName, String token);

}