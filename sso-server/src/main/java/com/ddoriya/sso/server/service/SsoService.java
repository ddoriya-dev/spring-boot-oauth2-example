package com.ddoriya.sso.server.service;

import com.ddoriya.sso.server.domain.AccessToken;

public interface SsoService {
	//
	AccessToken getAccessToken(String token, String clientId);
	
	String logoutAllClients(String clientId, String userName);
}
