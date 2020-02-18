package com.acompany.bsystem.service;

import javax.servlet.http.HttpServletRequest;

import com.acompany.bsystem.domain.Response;
import com.acompany.bsystem.domain.TokenRequestResult;

public interface OAuthService {
	//
	TokenRequestResult requestAccessTokenToAuthServer(String code, HttpServletRequest request);

	Response logout(String tokenId, String userName);

}