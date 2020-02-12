package com.playd.sso.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oauth_client_details")
public class Client {
	//
	@Id
	@Column(name="client_id")
	private String clientId;
	
	@Column(name="web_server_redirect_uri")
	private String redirectUri;
	
	@Column(name="logout_uri")
	private String logoutUri;
	
	@Column(name="base_uri")
	private String baseUri;

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		builder.append("OAuthClient [clientId=");
		builder.append(clientId);
		builder.append(", redirectUri=");
		builder.append(redirectUri);
		builder.append(", logoutUri=");
		builder.append(logoutUri);
		builder.append(", baseUri=");
		builder.append(baseUri);
		builder.append("]");
		return builder.toString();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getLogoutUri() {
		return logoutUri;
	}

	public void setLogoutUri(String logoutUri) {
		this.logoutUri = logoutUri;
	}

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

}
