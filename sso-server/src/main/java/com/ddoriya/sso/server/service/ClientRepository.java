package com.ddoriya.sso.server.service;

import org.springframework.data.repository.CrudRepository;

import com.ddoriya.sso.server.domain.Client;

public interface ClientRepository extends CrudRepository<Client, String> {
	//
}
