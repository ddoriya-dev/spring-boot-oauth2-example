package com.playd.sso.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playd.sso.server.domain.AccessToken;
import com.playd.sso.server.domain.Client;

@Service("ssoService")
public class SsoServiceImpl implements SsoService {
	//
	private static final Logger log = LoggerFactory.getLogger(SsoServiceImpl.class);
	
	@Autowired
	private AccessTokenRepository accessTokenRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public AccessToken getAccessToken(String token, String clientId) {
		//
		String tokenId = extractTokenId(token);
		
		return accessTokenRepository.findByTokenIdAndClientId(tokenId, clientId);
	}

	private String extractTokenId(String value) {
		//
		if (value == null) {
			//
			return null;
		}

		try {
			//
			MessageDigest digest = MessageDigest.getInstance("MD5");
			
			byte[] bytes = digest.digest(value.getBytes("UTF-8"));
			return String.format("%032x", new BigInteger(1, bytes));
		}
		catch (NoSuchAlgorithmException e) {
			//
			throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
		}
		catch (UnsupportedEncodingException e) {
			//
			throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
		}
	}
	
	@Override
	@Transactional
	public String logoutAllClients(String clientId, String userName) {
		//
		requestLogoutToAllClients(userName);
		
		removeAccessTokens(userName);
		
		Client client = clientRepository.findOne(clientId);
		
		return client.getBaseUri();
	}

	private void requestLogoutToAllClients(String userName) {
		//
		List<AccessToken> tokens = accessTokenRepository.findByUserName(userName);
		log.debug("\n## in requestLogoutToAllClients tokens.size : {}", tokens.size());
		
		for (AccessToken token : tokens) {
			//
			requestLogoutToClient(token);
		}
	}
	
	private void requestLogoutToClient(AccessToken token) {
		//
		Client client = clientRepository.findOne(token.getClientId());
		
		String logoutUri = client.getLogoutUri();
		log.debug("\n## in requestLogoutToClient logoutUri : {}", logoutUri);
	
		String authorizationHeader = null;
		
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("tokenId", token.getTokenId());
		paramMap.put("userName", token.getUserName());
		
		HttpPost post = buildHttpPost(logoutUri, paramMap, authorizationHeader);
		
		executePostAndParseResult(post, Object.class);
	}
	
	private HttpPost buildHttpPost(String reqUrl, Map<String, String> paramMap, String authorizationHeader) {
		//
		HttpPost post = new HttpPost(reqUrl);
		if (authorizationHeader != null) {
			//
			post.addHeader("Authorization", authorizationHeader);
		}
		
		List<NameValuePair> urlParameters = new ArrayList<>();
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		return post;
	}

	private <T> T executePostAndParseResult(HttpPost post, Class<T> clazz) {
		//
		T result = null;
		try {
			//
			HttpClient client = HttpClientBuilder.create().build();
			
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));
	
			StringBuffer resultBuffer = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				resultBuffer.append(line);
			}
			log.debug("\n## response body : '{}'", resultBuffer.toString());
			
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(resultBuffer.toString(), clazz);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		
		return result;
	}

	private int removeAccessTokens(String userName) {
		//
		return accessTokenRepository.deleteByUserName(userName);
	}
	
}
