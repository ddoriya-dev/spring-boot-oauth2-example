package com.acompany.asystem.service;

import org.springframework.data.repository.CrudRepository;

import com.acompany.asystem.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
	
}
