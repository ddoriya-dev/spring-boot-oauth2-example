package com.acompany.bsystem.service;

import org.springframework.data.repository.CrudRepository;

import com.acompany.bsystem.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
	
}
