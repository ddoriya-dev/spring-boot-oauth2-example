package com.ddoriya.sso.server;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	//
	private static final Logger log = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);
	
	@Autowired
	private AuthorizationCodeServices authorizationCodeServices;
	
	@Autowired
	private ApprovalStore approvalStore;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//
		endpoints
			.requestValidator(new AuthRequestValidator())
			.tokenStore(tokenStore)
			.authorizationCodeServices(authorizationCodeServices)
			.approvalStore(approvalStore);
	}

//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		//
//	}
	
	@Bean
	public AuthorizationCodeServices jdbcAuthorizationCodeServices(DataSource dataSource) {
		//
		log.debug("\n## in jdbcAuthorizationCodeServices()");
		return new JdbcAuthorizationCodeServices(dataSource);
	}
	
	@Bean
	public ApprovalStore jdbcApprovalStore(DataSource dataSource) {
		//
		log.debug("\n## in jdbcApprovalStore()");
		return new JdbcApprovalStore(dataSource);
	}
	
	@Bean
	@Primary
	public ClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
		//
		log.debug("\n## in jdbcClientDetailsService()");
		return new JdbcClientDetailsService(dataSource);
	}
	
	@Bean
	public TokenStore jdbcTokenStore(DataSource dataSource) {
		//
		log.debug("\n## in jdbcTokenStore()");
		return new JdbcTokenStore(dataSource);
	}

}

class AuthRequestValidator implements OAuth2RequestValidator {
	//
	private static final Logger log = LoggerFactory.getLogger(AuthRequestValidator.class);
	
	@Override
	public void validateScope(TokenRequest tokenRequest, ClientDetails client) throws InvalidScopeException {
		//
		log.debug("\n## validate TokenRequest : {}", tokenRequest);
		log.debug("\n## validate client : {}", client);
	}
	
	@Override
	public void validateScope(AuthorizationRequest authorizationRequest, ClientDetails client)
			throws InvalidScopeException {
		//
		log.debug("\n## validate authorizationRequest : {}", authorizationRequest);
		log.debug("\n## client : {}", client);
	}
}
