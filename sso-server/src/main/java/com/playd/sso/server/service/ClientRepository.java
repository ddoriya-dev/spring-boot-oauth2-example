package com.playd.sso.server.service;

import org.springframework.data.repository.CrudRepository;

import com.playd.sso.server.domain.Client;

public interface ClientRepository extends CrudRepository<Client, String> {
	//
}
