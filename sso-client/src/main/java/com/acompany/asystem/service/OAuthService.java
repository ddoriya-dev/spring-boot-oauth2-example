package com.acompany.asystem.service;

import javax.servlet.http.HttpServletRequest;

import com.acompany.asystem.domain.Response;
import com.acompany.asystem.domain.TokenRequestResult;

public interface OAuthService {
	//
	TokenRequestResult requestAccessTokenToAuthServer(String code, HttpServletRequest request);

	Response logout(String tokenId, String userName);

}