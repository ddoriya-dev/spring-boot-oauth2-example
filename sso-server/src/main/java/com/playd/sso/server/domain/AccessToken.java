package com.playd.sso.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oauth_access_token")
public class AccessToken {
	//
	@Id
	private String tokenId;
	
	private String token;
	
	@Column(name="user_name")
	private String userName;

	@Column(name="authentication_id")
	private String authenticationId;
	
	@Column(name="client_id")
	private String clientId;
	
	private String authentication;
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		builder.append("AccessToken [tokenId=");
		builder.append(tokenId);
		builder.append(", token=");
		builder.append(token);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append("]");
		return builder.toString();
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

//	public String getRefreshToken() {
//		return refreshToken;
//	}
//
//	public void setRefreshToken(String refreshToken) {
//		this.refreshToken = refreshToken;
//	}
	
	
}
