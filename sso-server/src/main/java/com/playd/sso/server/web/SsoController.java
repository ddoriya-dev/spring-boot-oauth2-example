package com.playd.sso.server.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.playd.sso.server.domain.AccessToken;
import com.playd.sso.server.domain.UserInfoResponse;
import com.playd.sso.server.service.SsoService;

@Controller
public class SsoController {
	//
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SsoService ssoService;
	
	@RequestMapping(value="/userInfo", method=RequestMethod.POST)
	@ResponseBody
	public UserInfoResponse userInfo(@RequestParam(name="token") String token,
			@RequestParam(name="clientId") String clientId) {
		//
		log.debug("\n## in user(). token : {}, clientId : {}", token, clientId);
		AccessToken accessToken = ssoService.getAccessToken(token, clientId);
		
		log.debug("\n## accessToken : '{}'", accessToken);
		
		UserInfoResponse response = new UserInfoResponse();
		if (accessToken == null) {
			//
			response.setResult(false);
			response.setMessage("사용자 정보를 조회할 수 없습니다.");
		}
		else {
			//
			response.setUserName(accessToken.getUserName());
		}
		
		return response;
	}
	
	@RequestMapping(value="/userLogout", method=RequestMethod.GET)
	public String userLogout(
			@RequestParam(name="clientId") String clientId,
			HttpServletRequest request) {
		//
		String userName = request.getRemoteUser();
		log.debug("\n## userLogout() : {}, {}, {}", clientId, userName);
		String baseUri = ssoService.logoutAllClients(clientId, userName);
		
		request.getSession().invalidate();
		
		return "redirect:" + baseUri;
	}
	
}
